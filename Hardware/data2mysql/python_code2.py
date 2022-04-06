import serial 
import pymysql
import time
import serial.tools.list_ports

#establish connection to MySQL. You'll have to change this for your database.
dbConn = pymysql.connect(host='localhost',user='root',password='Gz618kkk',database='ArduinoDB') or die ("could not connect to database")
#open a cursor to the database
cursor = dbConn.cursor()
dataline = []
portData = serial.tools.list_ports.comports()
for i in range(len(portData)):
    if (str(portData[i])[8:11]=="usb" and str(portData[i])[25]=="I"):
        device = (str(portData[i])[:22])
        print(device)
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
        print (pieces[0])
        print (pieces[1])

        try:
            cursor=dbConn.cursor()
            print ("hello")
            if pieces[1] != "Wait for valid data !":
                if int(pieces[0]) < 1000:
                    pieces[0] = str(1021)               
                cursor.execute("""INSERT INTO ArduinoDB.MEGA_data (HeartRate,BOS,HCHO) VALUES (%s,%s,%s)""", (pieces[1],str(int(pieces[0])/(int(pieces[1])+int(pieces[0]))),pieces[2]))
                pass
            print ("world")
            dbConn.commit()
            cursor.close()
        except pymysql.IntegrityError:
            print ("failed to insert data")
        finally:
            cursor.close()
    except:
        print ("Processing")
    
            
