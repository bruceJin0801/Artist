import javafx.geometry.Insets
import javafx.scene.control.*
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Line
import java.awt.Font


internal class ToolbarView (private val model: Model) : VBox(), IView{
    private fun layoutView() {
        val select = Button("",ImageView("select.png"))
        val erase = Button("",ImageView("eraser.png"))
        val line = Button("",ImageView("write.png"))
        val fill = Button("",ImageView("fill.png"))
        val circle = Button("",ImageView("circle.png"))
        val rectangle = Button("",ImageView("rectangle.png"))
        val rotate = Button("",ImageView("rotate.png"))
        val scale = Button("",ImageView("scale.png"))
        val bar1 = HBox(select, erase)
        val bar2 = HBox(line, fill)
        val bar3 = HBox(circle, rectangle)
        val bar4 = HBox(rotate, scale)
        var myline = Line()
        myline.strokeWidth = 5.0
        bar1.spacing = 15.0
        bar1.padding = Insets(5.0)
        bar2.spacing = 15.0
        bar2.padding = Insets(5.0)
        bar3.spacing = 15.0
        bar3.padding = Insets(5.0)
        bar4.spacing = 15.0
        bar4.padding = Insets(5.0)
        val box = VBox()
        box.spacing = 10.0
        model.colorPicker.value = Color.BLACK
        line.setOnMouseClicked{
            model.selection = "line"
        }
        rectangle.setOnMouseClicked{
            model.selection = "rectangle"
        }
        circle.setOnMouseClicked {
            model.selection = "circle"
        }
        select.setOnMouseClicked{
            model.selection = "select"
        }
        fill.setOnMouseClicked{
            model.selection = "fill"
        }
        erase.setOnMouseClicked{
            model.selection = "erase"
        }
        rotate.setOnMouseClicked{
            model.selection = "rotate"
        }
        scale.setOnMouseClicked{
            model.selection = "scale"
        }

            model.colorPicker.valueProperty().addListener(){_, old, new ->
                if(!model.Isselected) {
                    myline.stroke = new
                }else{
                    myline.stroke = model.colorPicker.value
                }
            }
        var linelable = Label("Line Color:")
        box.children.addAll(linelable, model.colorPicker)
        val box2 = VBox()
        box2.spacing = 10.0
        //thickness
        model.slider.value = 3.0
        model.slider.maxWidth = 130.0
        model.slider.max = 30.0
        model.slider.min = 0.0
        model.slider.isSnapToTicks = true
        model.slider.majorTickUnit = 5.0
        model.slider.isShowTickLabels = true
        model.slider.isVisible = true
        model.slider.valueProperty().addListener { _, old, new ->
                model.thickness = new.toString().substringBefore('.')
            if(!model.Isselected) {
                myline.strokeWidth = new.toDouble()
            }else{
                myline.strokeWidth = model.thickness.toDouble()
            }
        }
        model.colorPicker2.value = Color.WHITE
        model.colorPicker2.setOnAction {
            model.fill = model.colorPicker2.value
        }
        var filllable = Label("Fill Color:")
        box2.children.addAll(filllable, model.colorPicker2, model.slider)


        val hb = HBox()
        hb.spacing = 40.0
        val vb = VBox()
        val lable = Label()
        model.tg = ToggleGroup()
        model.b1 = RadioButton("1")
        model.b2 = RadioButton("2")
        model.b3 = RadioButton("3")
        model.b4 = RadioButton("4")
        model.b1.toggleGroup = model.tg
        model.b2.toggleGroup = model.tg
        model.b3.toggleGroup = model.tg
        model.b4.toggleGroup = model.tg
        model.b1.isSelected = true
        lable.graphic = ImageView("1.png")
        vb.children.addAll(model.b1, model.b2, model.b3,model.b4)
        vb.spacing = 10.0
        myline.startY = 100.0
        myline.endY = 18.0
        hb.children.addAll(vb,myline)

        model.tg.selectedToggleProperty().addListener { _, old,new ->
            if(new == model.b1){
                model.dash = "straight"
                myline.strokeDashArray.clear()
            }else if(new == model.b2){
                model.dash = "long"
                myline.strokeDashArray.clear()
                myline.strokeDashArray.addAll(2.0, 12.0, 4.0, 2.0)
            }else if(new == model.b3){
                model.dash = "dot"
                myline.strokeDashArray.clear()
                myline.strokeDashArray.addAll(0.01, 0.0, 0.01, 30.0)
            }else{
                model.dash = "dots"
                myline.strokeDashArray.clear()
                myline.strokeDashArray.addAll(0.0, 10.0, 0.0, 25.0)
            }
        }
        this.spacing = 10.0
        this.children.addAll(bar1,bar2,bar3, bar4, box, box2,hb)
    }

    init {
        layoutView()
        this.model.addView(this)
    }

    override fun update() {
    }


}


