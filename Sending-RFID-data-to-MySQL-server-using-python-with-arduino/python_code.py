import serial 
import pymysql
import time

#establish connection to MySQL. You'll have to change this for your database.
dbConn = pymysql.connect(host='localhost',user='root',password='Gz618kkk',database='ArduinoDB') or die ("could not connect to database")
#open a cursor to the database
cursor = dbConn.cursor()
dataline = []
device = '/dev/cu.usbmodem141301' #this will have to be changed to the serial port you are using
try:
  print ("Trying...")
  arduino = serial.Serial(device, 9600) 
except: 
  print ("Failed to connect on")
while True:
    time.sleep(1)
    try:
        data=arduino.readline().strip()
        dataline = data.decode("UTF-8")
        print (dataline)
        pieces=dataline.split(",")
        print (pieces[0],pieces[1],pieces[2],pieces[3])

        try:
            cursor=dbConn.cursor()
            print ("hello")
            cursor.execute("""INSERT INTO ArduinoDB.NANO_data (temp,humidity,pressure,voice,brightness) VALUES (%s,%s,%s,%s,%s)""", (pieces[0],pieces[1],pieces[2],pieces[3],"20.0"))
            print ("world")
            dbConn.commit()
            cursor.close()
        except pymysql.IntegrityError:
            print ("failed to insert data")
        finally:
            cursor.close()
    except:
        print ("Processing")
    
            
