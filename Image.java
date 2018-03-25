public class Image{
  //private properties/attributes
  private String metadata;
  private int maxRange;
  private Pixel[][] data;//I call this Pixel array, we will see later.
  
  //constructor
  public Image(String metadata, int maxRange, Pixel[][] dataInput){
    if(maxRange < 0){//checking if maxRange value is negative
      throw new IllegalArgumentException("Max Range cant be negative");
    }
    //here, maxRange is good!
    //initializing the attributes
    this.metadata = metadata;
    this.maxRange = maxRange;
    //initializing data array by storing a copy of the input array
    data = new Pixel[dataInput.length][dataInput[0].length];//the length of
    //the Pixel array is equal to the length 
    //of the array in the input arguments
    for(int i=0;i<this.data.length;i++){
      for(int j=0;j<data[i].length;j++){
        this.data[i][j] = dataInput[i][j];
      }
    }
  }
  
  //non-static get method for metadata
  public String getMetadata(){
    return this.metadata;
  }
  //non-static get method for max range
  public int getMaxRange(){
    return this.maxRange;
  }
  //non-static get method for width
  //width is the length of each sub-array
  public int getWidth(){
    return this.data[0].length;
  }
  //non-static get method for height
  //height is the length of the array
  public int getHeight(){
    return this.data.length;
  }
  //non-static get method for get pixel at a 
  //specific index
  public Pixel getPixel(int i, int j){
    return this.data[i][j];
  }
  //non-static flip method
  public void flip(boolean horizontal){
    //creating a 2d Pixel array
    Pixel[][] array = new Pixel[this.data.length][this.data[0].length];
    if(horizontal == true){//horizontal flip
      //{{1,2,3},{4,5,6}} -> {{3,2,1},{6,5,4}}
      for(int i=0;i<array.length;i++){
        for(int j=0;j<array[i].length;j++){
          //populating the new array with the flipped values
          array[i][this.data[0].length-j-1] = this.data[i][j];
        }
      }
    }
    else if(horizontal == false){//Vertical Flip
      //{{1,2,3},{4,5,6}} -> {{4,5,6},{1,2,3}}
      for(int i=0;i<array.length;i++){
        for(int j=0;j<array[i].length;j++){
          //populating the new array with the flipped values
          array[i][this.data[0].length-j-1] = 
            this.data[this.data.length-i-1][this.data[0].length-j-1];
        }
      }
    }
    //updating the 2d Pixel array of the calling image.
    this.data = array;
  }
  
  //non-static method that converts an image into the grey scale
  public void toGrey(){
    //convert each red green blue pixel into grey pixel
    for(int i=0;i<this.data.length;i++){
      for(int j=0;j<this.data[0].length;j++){
        int greyIntensity = this.data[i][j].grey();//saving the value of the intensity to 
        //initialize a new grey scale pixel
        Pixel greyPixel = new Pixel(greyIntensity);//creating a grey Pixel.
        //updating the Pixel array with the grey pixel object.
        this.data[i][j] = greyPixel;
      }
    }
  }
  //non-static crop method
  public void crop(int startX, int startY, int endX, int endY){
    //checking if the inputs are valid
    if(startX < 0 || startY <0|| startX > endX || startY > endY ||
       endX > this.data.length || endY > this.data[0].length){
      throw new IllegalArgumentException("invalid input");
    }
    //here, the inputs are valid!
    //creating a cropped pixel array of size (end - start)
    Pixel[][] croppedPixelArray = new Pixel[endX-startX][endY-startY];
    for(int i = 0;i<croppedPixelArray.length;i++){
      for(int j = 0;j<croppedPixelArray[i].length;j++){
        //populating the new array with the desired indices from the uncropped array
        croppedPixelArray[i][j] = this.data[startX+i][startY+j];
      }
    }
    //updating the Pixel array
    this.data = croppedPixelArray;
  }
}