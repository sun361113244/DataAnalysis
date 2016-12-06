object helloworld1
{
  def main(args: Array[String])
  {
    var msg = "hello , world!";
    var msg1:String = "hellow , world ,string";

    var greetStrings = new Array[String](3);
    greetStrings(0) = "hello";
    greetStrings(1) = ",";
    greetStrings(2) = "world\n";

    var i = 0;
    while(i<greetStrings.length){
      print(greetStrings(i) + " ")
      i+=1;
    }

    greetStrings.foreach((arg: String)=>print(arg + " "))
  }

  def max(x:Int , y:Int):Int =
  {
    if(x>y)
      return x;
    else
      return y;
  }

  def printHelloWorld() =
  {
    println("hello world!");
  }
}
