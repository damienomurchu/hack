#!/usr/bin/python3

from requests.auth import HTTPBasicAuth
import requests
import json 

class ghAccount:
  def __init__(self, username='', password='', token='', baseUrl='https://api.github.com'):
    self.username = username
    self.password = password
    self.token = token
    self.userAgent = username
    self.baseUrl = baseUrl
  
  def setUserName(self, userName):
    self.userAgent = userAgent 

  def setPassword(self, password):
    self.userAgent = userAgent

  def setToken(self, newToken):
    self.token =  newToken

  def setBaseUrl(self, baseUrl):
    self.baseUrl = baseUrl

# get list of repos
def listRepos(ghAccount, userName):
  repos = requests.get(ghAccount.baseUrl + '/search/repositories?q=user:' + userName, auth=HTTPBasicAuth(ghAccount.username, ghAccount.password))
  return repos.content

def main():
  username = input('>> Input your GitHub username: ')
  password = input('>> Input your GitHub password: ')

  dm = ghAccount(username, password)
  print('dm: ' + str(dm))
  print(str(listRepos(dm, 'damienomurchu')))
  
  #byteList = listRepos(dm, 'damienomurchu')
  #print(str(byteList))
  #parsed = json.loads(str(byteList))
  #print(json.dumps(parsed, indent=4, sort_keys=True))

if __name__ == '__main__':
  main()