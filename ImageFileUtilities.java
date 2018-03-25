import java.util.*;
import java.io.*;
public class ImageFileUtilities{
  //static method that reads a file and returns an Image.
  public static Image read(String filename) throws IOException{
    Image readTheImage;//declaring an Image variable.
    Pixel[][] pixelsObject;//to initialize an Image object later.
    
    //reading throw the file using the scanner
    Scanner sc = new Scanner(new File(filename));
    String fileSupport = sc.nextLine();//taking either a P2 or P3 header, we will need this later
    String comments = sc.nextLine();//taking the comment.
    //checking if the file has more than one comment;
    //as long as the file has metadata, update the comment variable with the next metadata.
    while(sc.hasNext("#")){
      comments = sc.nextLine();
    }
    //here, we are done with the comments!
    int width = sc.nextInt();//getting the width of the image
    int height = sc.nextInt();//getting the height of the image
    int maxRange = sc.nextInt();//getting the max intensity the image has
    pixelsObject = new Pixel[height][width];//initializing Pixel array with the height and the width.
    
    //here, we are filling the Pixel array with the pixels that the file that we are reading has.
    //if the file is of type PGM, it will follow this code
    if(fileSupport.equals("P2")){
      for(int i=0;i<pixelsObject.length;i++){
        for(int j=0;j<pixelsObject[i].length;j++){
          int intensityOfPixels = sc.nextInt();//storing the value to create a grey scale pixel image.
          Pixel pixels = new Pixel(intensityOfPixels);//using the grey scale constructur;
          //one number per pixel(red = green = blue = intensityOfPixels
          pixelsObject[i][j] = pixels;//filling the array with pixels
        }
      }
    }
    //if the file is of type PNM, it will follow this code
    else if(fileSupport.equals("P3")){
      for(int i=0;i<pixelsObject.length;i++){
        for(int j=0;j<pixelsObject[i].length;j++){
          int r = sc.nextInt();//for red
          int g = sc.nextInt();//for green
          int b = sc.nextInt();//for blue
          Pixel pixels = new Pixel(r,g,b);//using the overloading constructor 
          //that takes red, green and blue intensities
          pixelsObject[i][j] = pixels;//filling the array with pixels
        }
      }
    }
    //if the supporting file is neither PGM nor PNM, we throw an exception.
    else{
      sc.close();//closing the scanner, i have to put one here apart from the one 
      //below otherwise it gives me a warning.
      throw new IOException("Invalid supporting file");
    }
    sc.close();//closing the scanner
    readTheImage = new Image(comments,maxRange,pixelsObject);//initializing the Image object 
    //with what we took from reading the file
    return readTheImage;
  }
//method for writing the PNM file 
  public static void writePnm(Image imageInput,String filename) throws IOException{
    FileWriter fileWriter = new FileWriter(filename);//creates a file writer
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);//creates a buffered writer
    int height = imageInput.getHeight();//assigning the height of the image from imageInput.
    int width = imageInput.getWidth();//assigning the width of the image from imageInput.
    int maxRange = imageInput.getMaxRange();//assigning the max Intensity of the image from imageInput.
    
    //writing the image
    bufferedWriter.write("P3 \n");//writing the file support of the written image
    bufferedWriter.write(width + " " + height + "\n");//writing the width and the height of the written image 
    bufferedWriter.write(maxRange + "\n");//writing the max intesity the written image has
    for(int i=0;i<height;i++){
      for(int j=0;j<width;j++){
        //writing each pixel
        bufferedWriter.write(imageInput.getPixel(i,j).getRed()+" ");//for red
        bufferedWriter.write(imageInput.getPixel(i,j).getGreen()+" ");//for green
        bufferedWriter.write(imageInput.getPixel(i,j).getBlue()+" ");//for blue
      }
      bufferedWriter.write("\n");
    }
    bufferedWriter.close();//closing the buffered writer.
    fileWriter.close();//closing the file writer.
  }
  
//method for writing the PGM file
  public static void writePgm(Image imageInput, String filename) throws IOException{
    FileWriter fileWriter = new FileWriter(filename);//creates a file writer
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);//creates a buffered writer
    imageInput.toGrey();//converting to a grey scale image
    int height = imageInput.getHeight();//assigning the height of the image from imageInput.
    int width = imageInput.getWidth();//assigning the width of the image from imageInput.
    int maxRange = imageInput.getMaxRange();//assigning the max Intensity of the image from imageInput.
    
    //writing the image
    bufferedWriter.write("P2 \n");//writing the file support of the written image
    bufferedWriter.write(width + " " + height + "\n");//writing the width and the height of the written image
    bufferedWriter.write(maxRange + "\n");//writing the max intesity the written image has
    for(int i=0;i<height;i++){
      for(int j=0;j<width;j++){
        bufferedWriter.write(imageInput.getPixel(i,j).getRed()+" ");//since the 
        //intensity of red = green = blue, i just chose to get the intensity of red.
      }
      bufferedWriter.write("\n");
    }
    bufferedWriter.close();//closing the buffered writer.
    fileWriter.close();//closing the file writer.
  }
}