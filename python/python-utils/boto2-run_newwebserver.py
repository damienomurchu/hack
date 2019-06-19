#!/usr/bin/python3

import boto.ec2
from boto2-instance import *
import time
import boto2-ssh
import boto2-utils

# gets amazon region from user, returning a region code
def getRegion():
  # solicit & validate region code, connecting if valid
  while True:
    region = input('>> What is your AWS region: ')
    if connUtils.validRegion(region):
      conn = boto.ec2.connect_to_region(region)
      break
  return conn

# gets name for instance from user, returning a name string
def getName():
  # solicit, validate & verify instance name for new instance
  nameChoice = ''
  while True:
    nameChoice = input('>> Select a name for your new instance: ')
    if nameChoice != '':
      break
  return nameChoice

# gets pem key to be used for instance, from user
def getKey(conn):
  # solicit, validate and assign chosen PEM for new instance
  keyChoice = ''
  print('Retrieving registered PEM keys...')
  pemKeys = connUtils.getPemKeys(conn)
  while True:
    keyChoice = input('>> Choose your PEM key ' + str(pemKeys) + ': ')
    if connUtils.validPem(conn, keyChoice):
      break
    else:
      print('Invalid key choice, please choose again')
  return keyChoice

# gets security group for instance, from user
def getSecGroup(conn):
  secChoice = ''
  secGroups = connUtils.getSecurityGroups(conn)
  while True:
    secChoice = input('>> Choose a security group ' + str(secGroups) +
                      '\n(leave blank to choose an auto-created default with HTTP and SSH access): ')
    if (secChoice in secGroups):
      break
    if (secChoice == ''):
      name = getNewGroupName(secGroups)
      connUtils.createNewSecGroup(conn, name)
      secChoice = name
      break
  return secChoice

#
def getNewGroupName(secGroups):
  name = ''
  while (name == '') or (name in secGroups):
    name = input('>> Choose a name for your new security group: ')
  return name


# creates an instance with the specified connection and instance object
def createInstance(conn, instanceObj):
  reservation = conn.run_instances('ami-b04e92d0', key_name=instanceObj.pemKey, instance_type='t2.micro',
                                   security_groups=[instanceObj.secGroup])
  instance = reservation.instances[0]
  instance.add_tag('Name', instanceObj.name)
  print('New instance now created...')

  # update instance object with ip address once instance fully started
  while (instance.state != 'running'):
    print('Waiting for instance to start... ')
    time.sleep(5)
    instance.update()
  instanceObj.setIP(instance.ip_address)
  print('Instance now started.')

  return instance

# installs nginx on a specified instance by updating packages, installing python35 & then nginx
def installNginx(instance):
  ssh.executeOnInstance(instance, 'sudo yum -y update')
  ssh.executeOnInstance(instance, 'sudo yum -y install python35')
  ssh.executeOnInstance(instance, 'sudo yum -y install nginx')

# copies check_webserver.py to instance & runs it on instance
def copyCheckWebServer(instance):
  ssh.copyToInstance(instance, 'check_webserver.py')
  ssh.executeOnInstance(instance, 'chmod 700 check_webserver.py')
  ssh.executeOnInstance(instance, './check_webserver.py')


def stopInstance(instance):
  print('Now stopping vm...')
  instance.stop()
  while (instance.state != 'stopped'):
    print('Waiting for instance to stop... ')
    time.sleep(5)
    instance.update()
  print('Instance now stopped.')

def main():

  # get connection region from user
  conn = getRegion()

  # gather details for new instance
  instName = getName()
  instKey = getKey(conn)
  secGrp = getSecGroup(conn)

  # store instance settings in an object & create a new AWS instance
  instanceDetails = instance(name=instName, key=instKey, sec=secGrp)
  AWSinstance = createInstance(conn, instanceDetails)

  # sleep to allow instance to fully initialise
  print('Sleeping for 60 secs now to allow instance to self-actualise...')
  time.sleep(60)

  # update & install python3 and nginx
  installNginx(instanceDetails)

  # copy script to server & run it
  copyCheckWebServer(instanceDetails)

  # stop instance
  stopInstance(AWSinstance)


if __name__ == '__main__':
  main()