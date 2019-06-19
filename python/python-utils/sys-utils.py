#!usr/bin/python3

import subprocess
import os

# checks if a process is running; returns True if it is, otherwise False
def processRunning(process):
  cmd = 'ps -A | grep ' + process + ' | grep -v grep'
  (status, output) = subprocess.getstatusoutput(cmd)
  if status > 0:
    return False
  else:
    return True

# starts a specified process
def startProcess(process):
  cmd = 'sudo service ' + process + ' start'
  #subprocess.call(cmd)
  os.system(cmd)

# stops a specified process
def stopProcess(process):
  cmd = 'sudo service ' + process + ' stop'
  os.system(cmd)

# returns the number of running processes
def runningProcesses():
  cmd = 'ps -A | grep -cv grep'
  (status, output) = subprocess.getstatusoutput(cmd)
  return output

# list processes by cpu usage
def cpuUsage():
  cmd = 'top -b -n 1 | head -n 11 | tail -n 5'
  os.system(cmd)