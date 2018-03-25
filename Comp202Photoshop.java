import java.io.*;
public class Comp202Photoshop{
  public static void main(String[] args){
    //checking if the user has input at least 4 arguments
    if(args.length <4){
      System.out.println("the format of running this code is typing the following" + 
                         " in the command line arguments in the following order:" +
                         " the name of input file, the name of the output file," +
                         " the file format you want it to be in(it can be either pnm or pgm) and "+
                         " the operation you would like to perform(-gs,-fh,-fv,-cr)");
    }
    //here, the user has input at least 4 arguments!
    else{
      String input = args[0];
      String output = args[1];
      String format = args[2];
      String operation = args[3];
      
      if(operation.equals("-cr")){//if the operation is to crop, it follows this code
        if(format.equals("pnm")){//comparing the contents in the string, 
          //if the user wants it in pnm, it follows this code
          try{
            int startX = Integer.parseInt(args[4]);
            int startY = Integer.parseInt(args[5]);
            int endX = Integer.parseInt(args[6]);
            int endY = Integer.parseInt(args[7]);
            Image loadFile = ImageFileUtilities.read(input);//since read is a static method
            loadFile.crop(startX,startY,endX,endY);
            ImageFileUtilities.writePnm(loadFile, output);//since writePnm is a static method
          }
          catch(IOException e){//if the file doesnt exist, we catch the exception
            System.out.println("can't find file or the file doesnt match the proper format");
          }
          catch(ArrayIndexOutOfBoundsException a){//if the user didnt input EXACTLY 8 arguments,
            //we catch the exception
            System.out.println("it is required that there be 8 arguments total, the last 4 of which should be int");
          }
          catch(NumberFormatException n){//if the last 4 arguments arent of type int, we catch the exception
            System.out.println("the last 4 arguments must be of type int");
          }
        }
        else if(format.equals("pgm")){//comparing the contents in the string,
          //if the user wants it in pnm, it follows this code
          try{//we want to check that the user has input exactly 8 arguments
            int startX = Integer.parseInt(args[4]);
            int startY = Integer.parseInt(args[5]);
            int endX = Integer.parseInt(args[6]);
            int endY = Integer.parseInt(args[7]);
            Image loadFile = ImageFileUtilities.read(input);//since read is a static method
            loadFile.crop(startX,startY,endX,endY);
            ImageFileUtilities.writePgm(loadFile, output);//since writePgm is a static method
          }
          catch(IOException e){//if the file doesnt exist, we catch the exception
            System.out.println("can't find file");
          }
          catch(ArrayIndexOutOfBoundsException d){//if the user didnt input EXACTLY 8 arguments,
            //we catch the exception
            System.out.println("it is required that there be 8 arguments total, the last 4 of which should be int");
          }
          catch(NumberFormatException n){//if the last 4 arguments arent of type int, we catch the exception.
            System.out.println("the last 4 arguments must be of type int");
          }
        }
        else{//if the format, is neither pnm nor pgm, we give a friendly message and exit.
          System.out.println("Invalid format. The format is either pnm or pgm :)");
        }
      }
      //}
      //if the operation is to convert an image to grey scale, it follows this code
      else if(operation.equals("-gs")){
        if(format.equals("pgm")){//checking if the format is correct
          try{
            Image loadFile = ImageFileUtilities.read(input);
            ImageFileUtilities.writePgm(loadFile, output);
          }
          catch(IOException e){//if the file doesnt exist, we catch the exception
            System.out.println("can't find file");
          }
        }
        else{//if the format is not pgm we give a friendly message and exit.
          System.out.println("invalid format. format has to be pgm");
        }
      }
      //if the operation is to flip the image horizontally, it follows this code
      else if(operation.equals("-fh")){//comparing the contents in the string,
        //if the user wants it in pnm, it follows this code
        if(format.equals("pnm")){
          try{
            Image loadFile = ImageFileUtilities.read(input);
            loadFile.flip(true);
            ImageFileUtilities.writePnm(loadFile, output);
          }
          catch(IOException e){//if the file doesnt exist, we catch the exception
            System.out.println("can't find file");
          }
        }
        else if(format.equals("pgm")){//comparing the contents in the string,
          //if the user wants it in pgm, it follows this code
          try{
            Image loadFile = ImageFileUtilities.read(input);
            loadFile.flip(true);
            ImageFileUtilities.writePgm(loadFile, output);
          }
          catch(IOException e){//if the file doesnt exist, we catch the exception
            System.out.println("can't find file");
          }
        }
        //if the format, is neither pnm nor pgm, we give a friendly message and exit.
        else{
          System.out.println("Invalid format. The format is either pnm or pgm :)");
        }
      }
      //if the operation is to flip the image vertically, it follows this code
      else if(operation.equals("-fv")){//comparing the contents in the string,
        //if the user wants it in pnm, it follows this code
        if(format.equals("pnm")){
          try{
            Image loadFile = ImageFileUtilities.read(input);
            loadFile.flip(false);
            ImageFileUtilities.writePnm(loadFile, output);
          }
          catch(IOException e){//if the file doesnt exist, we catch the exception
            System.out.println("can't find file");
          }
        }
        else if(format.equals("pgm")){//comparing the contents in the string,
          //if the user wants it in pgm, it follows this code
          try{
            Image loadFile = ImageFileUtilities.read(input);
            loadFile.flip(false);
            ImageFileUtilities.writePgm(loadFile, output);
          }
          catch(IOException e){//if the file doesnt exist, we catch the exception
            System.out.println("can't find file");
          }
        }
        //if the format, is neither pnm nor pgm, we give a friendly message and exit.
        else{
          System.out.println("Invalid format. The format is either pnm or pgm :)");
        }
      }
      //if the operation is none of the four stated above, we give a friendly message and exit.
      else{
        System.out.println("Invalid Operation. The operations are: -fh = flip horizontal;"+
                           "-fv = flip vertical; -gs = converting into grey scale;"+
                           "and -cr = cropping the image with the desired indices :)");
      }
    }
  }
}