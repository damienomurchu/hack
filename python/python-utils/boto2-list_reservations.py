#!/usr/bin/python3

import boto.ec2
import sys

def connect():
    print('Opening connection... ')
    conn =  boto.ec2.connect_to_region("us-west-2")
    if conn:
        return conn
    else:
        print('Error: failed to connect to EC2 region')
        sys.exit(1)

def list_reservations(conn):
    reservations = conn.get_all_reservations()

    for res in reservations:
        for inst in res.instances:
            if 'Name' in inst.tags:
                print("%s (%s) [%s]" % (inst.tags['Name'], inst.id, inst.state))
            else:
                print("%s [%s]" % (inst.id, inst.state))


# Define a main() function.
def main():
    connection = connect()
    list_reservations(connection)
    sys.exit(0)
      
# This is the standard boilerplate that calls the main() function.
if __name__ == '__main__':
    main()
