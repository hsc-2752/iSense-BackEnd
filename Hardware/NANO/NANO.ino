#include <Arduino_HTS221.h>
#include <Arduino_LPS22HB.h>
#include <Arduino_APDS9960.h>
#include <PDM.h>
#include <math.h>

static const char channels = 1;      // default number of output channels
static const int frequency = 16000;  // default PCM output frequency
short sampleBuffer[512];   // Buffer to read samples into, each sample is 16bits
volatile int samplesRead;  // Number of audio samples read


void setup() {
  Serial.begin(9600);
  while (!Serial);

  if (!HTS.begin()) {
    Serial.println("Failed to initialize humidity temperature sensor!");
    while (1);
  }
  if (!BARO.begin()) {
    Serial.println("Failed to initialize pressure sensor!");
    while (1);
  }

  if (!APDS.begin()) {
        Serial.println("Error initializing APDS9960 sensor.");
        while (true); // Stop forever
    }

  Serial.begin(9600);
  PDM.onReceive(onPDMdata);
  PDM.setGain(20);
  if (!PDM.begin(channels, frequency)) {
      Serial.println("Failed to start PDM!");
      while (1);
  }

  
}

int r = 0, g = 0, b = 0,a = 0;
void loop() {
  // read all the sensor values
  float temperature = HTS.readTemperature();
  float humidity    = HTS.readHumidity();
  // read the sensor value
  float pressure = BARO.readPressure();

  // print each of the sensor values
  //Serial.print("Temperature = ");
  Serial.print(temperature);
  Serial.print(",");
  //Serial.println(" °C");

  //Serial.print("Humidity    = ");
  Serial.print(humidity);
  Serial.print(",");
  //Serial.println(" %");

  // print an empty line
  //Serial.println();

  // wait 1 second to print again
  // print the sensor value
  //Serial.print("Pressure    =  ");
  Serial.print(pressure);
  Serial.print(",");
  //Serial.println(" kPa");

  // print an empty line
  //Serial.println();

  // wait 1 second to print again

  double spl = 0;  // 环境噪声 dB
  double amp = 0;  // amp: amplitude of sound
  double sum = 0;
  int cnt = 0;     // max: 1023, 15.625Hz

  while (cnt<1020) { // 1024
    if (samplesRead) {
        for (int i=0; i<samplesRead; i++) {
            sum += double(sampleBuffer[i])*sampleBuffer[i];
          }
        cnt += samplesRead;
        samplesRead = 0;
      }
    amp = 20 * log10(10*sqrt(sum/cnt));
  }
  spl = amp;

  //Serial.print("Voice       =  ");
  Serial.print(spl);
  Serial.print(",");
  //Serial.println(" dB");


//  int r = 0, g = 0, b = 0,a = 0;
  
  // check if a color reading is available
    if (APDS.colorAvailable()) {
        APDS.readColor(r, g, b, a);
    }
  Serial.println(a);
  
  delay(1000);
}

void onPDMdata()
{
    int bytesAvailable = PDM.available();
    PDM.read(sampleBuffer, bytesAvailable);
    samplesRead = bytesAvailable / 2;
}
