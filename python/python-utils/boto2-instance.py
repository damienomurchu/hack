#!/usr/bin/python3

# class represents an Amazon AWS instance
class instance:
  def __init__(self, name, key, ip="", sec=""):
    self.name = name
    self.pemKey = key
    self.ipAddress = ip
    self.secGroup = sec

  def getName(self):
    return self.name

  def getKey(self):
    return self.pemKey

  def getIP(self):
    return self.ipAddress

  def setIP(self, ip):
    self.ipAddress = ip

  def setSec(self, securityGroup):
    self.secGroup = securityGroup
