#!/usr/bin/python3

# ssh utility class to facilitate remote connections to AWS instances

import subprocess
from instance import *

# validates a pem key and adds a .PEM extension if needed
def validKey(key):
  if (key[-4:].lower() == '.pem'):
    return key
  else:
    return key + '.pem'

# Connects to an instance via ssh
def connectToInstance(instance):
  cmd = 'ssh -o StrictHostKeyChecking=no -i ~/' + validKey(instance.getKey()) + ' ec2-user@' + instance.getIP()
  (status, output) = subprocess.getstatusoutput(cmd)

  if status > 0:
    print('Error in establishing connection')
  else:
    print('Connection established')

# Connects to an instance, and executes a command on that instance
def executeOnInstance(instance, command):
  cmd = 'ssh -o StrictHostKeyChecking=no -i ~/' + validKey(instance.getKey()) + ' ec2-user@' + instance.getIP() + ' ' + command
  (status, output) = subprocess.getstatusoutput(cmd)

  if status > 0:
    print('Command could not be executed on vm successfully:\n[ ' + cmd + ' ]')
  else:
    print('Command executed on vm successfully:\n[ ' + command + ' ]\nOutput:\n' + output)

# Securely copies a file to an instance
def copyToInstance(instance, filename, folder=""):
  cmd = 'scp -i ~/' + validKey(instance.getKey()) + ' ./' + filename + ' ec2-user@' + instance.getIP() + ':.' + folder
  (status, output) = subprocess.getstatusoutput(cmd)

  if status > 0:
    print('File could not be copied successfully:\n[ ' + cmd + ' ]')
  else:
    print('File copied successfully:\n[ ' + filename + ' ]')