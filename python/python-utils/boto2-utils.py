#!/usr/bin/python3
# helper/ utility class that provides a number of connection-related helpers

import boto.ec2

# return a list of all security group names registered on AWS
def getSecurityGroups(conn):
  secGroups = conn.get_all_security_groups()
  list = []
  for group in secGroups:
    list.append(group.name)
  return list

# checks if region is a valid region
def validRegion(region):
  for reg in boto.ec2.regions():
    if (reg.name == region):
      return True

# validates pem choice against key pair list on AWS
def validPem(conn, key):
  # remove pem extension (if present), for comparison
  if (key[-4:].lower() == '.pem'):
    key = key[:-4]

  # retrieve registered AWS keypairs & check user choice against
  keys = conn.get_all_key_pairs()
  for keyPair in keys:
    if (keyPair.name == key):
      return True

# creates a new default security group with http & ssh enabled
def createNewSecGroup(conn, name):
  secgroup = conn.create_security_group(name, 'Only HTTP and SSH')
  secgroup.authorize('tcp', 80, 80, '0.0.0.0/0') # HTTP
  secgroup.authorize('tcp', 22, 22, '0.0.0.0/0') # SSH
  print('New security group [ ' + name + ' ] with HTTP and SSH access created')

def getPemKeys(conn):
  allKeys = conn.get_all_key_pairs()
  keys = []
  for keyPair in allKeys:
    keys.append(keyPair.name)
  return keys