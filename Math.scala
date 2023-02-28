import java.awt.{Color, Font}
import java.awt.image.BufferedImage
import java.io.File
import java.util.Scanner
import javax.imageio.ImageIO

object Math {
  val path = "./rsc/graph2.png"
  val width = 1024
  val height = 1024
  val dx = 0.001
  val d = 20.0 //zoom
  val function = "f(x) = sin(x) * 3 + x "
  val fontSize = 25
  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)
    def f(x:Double): Double =  {
      math.sin(x) * 3 + x
    }
    def df(x:Int):Double = {
      (f(x + dx) - f(x)) / dx
    }
    print("grid? (true / false) : ")
    val grid = sc.nextBoolean()
    val file = new File(path)
    val img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    val g = img.getGraphics
    g.setColor(Color.WHITE)

    g.setFont(new Font("Arial", Font.ITALIC, fontSize))
    g.drawString(function, 0, height / 50 + 10)

    g.setColor(Color.GRAY)
    for(i <- width/2 until width by d.toInt){
      if(grid){
        g.drawLine(i, 0, i, height)
        g.drawLine(width - i,  0, width - i, height)
      }else{
        g.drawLine(i, height / 2 - 4, i, height / 2 + 4)
        g.drawLine(width - i, height / 2 - 4, width - i, height / 2 + 4)
      }
    }
    for (i <- height / 2 until height by d.toInt) {
      if(grid){
        g.drawLine(0 , i, width , i)
        g.drawLine(0 , height - i, width, height - i)
      }else {
        g.drawLine(width / 2 - 4, i, width / 2 + 4, i)
        g.drawLine(width / 2 - 4, height - i, width / 2 + 4, height - i)
      }
    }
    g.setColor(Color.WHITE)
    g.drawLine(width/2 , 0 , width/2 , height)
    g.drawLine(0 , height/2 , width , height/2)


    g.setColor(Color.RED)
    g.setFont(new Font("Arial", Font.ITALIC, fontSize))
    g.drawString("positive integer", 0, height / 50 + 10 + fontSize)
    var i =0
    while (i < width/2){
      val x = i / d
      g.drawLine((x * d + width / 2).toInt, height / 2 - (f(x) * d ).toInt , (x * d + 0.1 + width / 2).toInt, height / 2 - (f(x + 0.5) * d).toInt)
      i += 1
    }
    g.setColor(Color.BLUE)
    g.setFont(new Font("Arial", Font.ITALIC, fontSize))
    g.drawString("negative integer", 0, height / 50 + 10 + fontSize*2)
    var j = -width/2
    while (j < 0) {
      val x = j / d
      g.drawLine((x * d + width / 2).toInt, height / 2 - (f(x) * d).toInt, (x * d + 0.1 + width / 2).toInt, height / 2 - (f(x + 0.5) * d).toInt)
      j += 1
    }
    g.dispose()
    try {
      ImageIO.write(img, "png", file)
      println("SUCCESS")
    } catch {
      case exception: Exception => exception.printStackTrace()
    }
  }
}

