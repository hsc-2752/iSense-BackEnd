# iSense-BackEnd

#### Developer version

To install and run **Isense** server on your computer:

-----

#### Running Environment: 

OS: Windows 10

JDK 17

Python 3.6 (for eeg and arduino)

Mind+(For Heart rate sensor (***DFRobot***) and Air pollution sensor(***MQ135***)) 

Arduino (For Temperature & humidity, illumination, decibel, pressure sensor,   )

Training Data-set: [SEED Dataset (sjtu.edu.cn)](https://bcmi.sjtu.edu.cn/home/seed/)



#### Database Setup

-----

* Software: ***MySQL***, ***MySQL*** ***Workbench***(Database Visualization)
* Setup: 
  * Create an account of ***MySQL***
  * import database files



#### Server Running

---

* Install [IntelliJ IDEA](https://www.jetbrains.com/zh-cn/idea/download/#section=windows)
* Open ***backend*** project with [IntelliJ IDEA](https://www.jetbrains.com/zh-cn/idea/download/#section=windows)
* Run calss ***BackendApplication***



#### Connect All Sensors

----

Follow the IMAGE//TODO to connect all sensor



#### HR & Blood Oxygen Saturation & Air Pollution Data OBTAIN

---

* Hardware

  * ***DFRobot***
  * ***MQ135***

  

* Obtain HR, blood oxygen saturation and air pollution index data

  * Install [Mind+](https://mindplus.cc/)
  * Select ***Board*** MEGA2560
  * Modify the line ? in python code
    * 改数据库信息
  * Modify the line ? in arduino code
    * 
    * 添加头文件
  * 
    * Select ***Tools*** in Arduino's dock bar
    * Select Library Manager
    * Search ***Header Filers *** then install them.
  * Run //TODO code
  * Run //TODO python代码





#### Other Data Obtain

------

* Hardware 

  ***NANO 33 ble*** (Integrates all other sensors)

* Obtain illumination, decibel, pressure, temperature, humidity data

  * Install [Arduino](https://www.arduino.cc/)

  * Configuring the running environment

  * Modify the line ? in code

  * Run code(TODO) in arduino terminal

  * Run python code(TODO)

    

#### EEG Data Obtain

----

* Headset Support: Emotiv+

* Obtain eeg data from emotiv plus equipment and get emotion recognition  
  * Install [Anaconda ](https://www.anaconda.com/) software
  * Create a new environment for running code
  * Activate environment 
  * Modify the  line 1357 in ***eeg.py***, the 1st value is database ip, the 2nd value is user name of MySQL, the 3rd value is the password.
  * Run `python .\CyKIT.py 127.0.0.1 5555 6 outputraw`
  * Install all needed libraries 
    * mysql-connector-python
    * mysqlclient
    * numpy
    * sklearn
    * torch
    * pythorch
    * _pytorch_select
    * intel-openmp
    * ninja
    * scikit-learn
  * Insert the USB of Emotiv+
  * Run `python .\CyKIT.py 127.0.0.1 5555 6 outputraw`
  * Open the ***Web\CyKIT.html*** file
  * Change the port to ***5555*** and click the ***Connect*** button



