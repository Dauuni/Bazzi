#include <Wire.h>
#include <Adafruit_MLX90614.h>

Adafruit_MLX90614 mlx = Adafruit_MLX90614();

//SCL=A5,SDA=A4
 
void setup() {
  Serial.begin(9600);

  mlx.begin();  //mlx모듈을 읽어들이기 시작
  Wire.begin();
}

void loop() {
  gasPin=analogRead(A3);
  volume=analogRead(A0);
  Serial.print(mlx.readObjectTempC()+10);   //체온 출력
  Serial.print(",");

  Wire.requestFrom(0xA0 >> 1, 1);    
  while(Wire.available()) {              //읽을 값이 있다면
        unsigned char c = Wire.read();   //센서 값 읽기
        Serial.println(c, DEC);            //맥박 출력
    }

  delay(1000);
}
