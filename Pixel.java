public class Pixel{
  //private properties/attributes
  private int red;
  private int green;
  private int blue;
  
  //first contructor; pnm
  public Pixel(int r, int g, int b){// r for red, g for green, b for blue
    if(r > 256 || r<0|| g >256 || g<0|| b>256 || b<0){//if any of these colors 
      //intensity are not in the range of [0,256], we throw an exception
      throw new IllegalArgumentException("invalid input for intensity");
    }
    this.red = r;
    this.green = g;
    this.blue = b;
  }
  //second contructor for grey scale
  public Pixel(int intensity){
    if(intensity > 256 || intensity <0){//if the  intensity isnt not 
      //in the range of [0,256], we throw an exception
      throw new IllegalArgumentException("invalid input for intensity");
    }
    //grey scale = red, green and blue have the same intensity.
    this.red = intensity;
    this.green = intensity;
    this.blue = intensity;
  }
  
  //non-static get method for Red
  public int getRed(){
    return this.red;
  }
  //non-static get method for Green
  public int getGreen(){
    return this.green;
  }
  //non-static get method for Blue
  public int getBlue(){
    return this.blue;
  }
  //non-static method representing grey scale
  //red = 0,green = 100, blue = 200
  //avg = 100
  public int grey(){
    int avg = ((this.red + this.green + this.blue)/3);
    return avg;
  }
}