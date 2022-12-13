# Image Encryption
A fun attempt at applying Vigenere Cipher to images

Note: The project was made and tested only on JDK8. It might or might not work on other versions.
You can downoad JDK8 from [here](https://www.openlogic.com/openjdk-downloads?field_java_parent_version_target_id=416&field_operating_system_target_id=All&field_architecture_target_id=391&field_java_package_target_id=396)

## Usage
Download the JAR file from releases section. 

Just like Vigenere Cipher it uses a main data and key. The only difference is tha both data and key should be images.
The dimensions don't need to be same the program will adjust the dimensions of the key_image by itself.

The syntax to use the program is 
```java
java -jar image-encryption.jar /path/to/main_image.jpg /path/to/key_image.jpg -O /path/to/outputImage.jpg
```
Example:
```java
java -jar image-encryption.jar ~/Pictures/taj_mahal.jpg ~/Pictures/joker.jpg -O ~/Pictures/generated_image.png
```

## Showcase
Encrypted image of `samples/taj_mahal.jpg` by using `samples/eiffel_tower.jpg` as a key
Notice how the program matches the dimensions of key image to that of main image
![Taj Mahal image Encrypted using the Eiffel Tower image. ](samples/new_wonder.png)

Encrypted image of `samples/hagia_sophia.jpg` using `samples/nasir_al_mulk.jpg` as key.

![Hagia Sophia image encrypted using Nasir-al-Mulk mosque image as key](samples/turko_persian.png)

Encrypted image of `samples/taj_mahal.jpg` using `samples/hagia_sophia.jpg` as key
![Taj Mahal image encrypted using Hagia Sophia image as a key](samples/indo_turkic_arch.png)
