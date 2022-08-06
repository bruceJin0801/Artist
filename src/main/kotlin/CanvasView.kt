import javafx.scene.layout.Pane
import javafx.scene.shape.Line
import javafx.event.EventHandler
import javafx.scene.input.KeyCode
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Ellipse
import javafx.scene.shape.Rectangle
import javafx.scene.shape.Shape
import javafx.scene.transform.Rotate
import java.lang.Math.pow
import kotlin.math.*


internal class CanvasView (private val model: Model) : Pane(), IView {
    internal enum class STATE {
        SELECTION, DRAWING, NONE, DRAG
    }
    private fun dash ( line: Shape ,str: String){
        if(str == "long"){
            line.strokeDashArray.clear()
            line.strokeDashArray.addAll(2.0, 12.0, 4.0, 2.0)
        }else if(str == "dot"){
            line.strokeDashArray.clear()
            line.strokeDashArray.addAll(0.01, 0.0, 0.01, 30.0)
        }else if(str == "dots") {
            line.strokeDashArray.clear()
            line.strokeDashArray.addAll(0.0, 10.0, 0.0, 25.0)
        }else{
            line.strokeDashArray.clear()
        }
    }

    private fun select (line: Shape): String{
        var str = "straight"
        if(line.strokeDashArray.isEmpty()){
            model.b1.isSelected = true
        }else if(line.strokeDashArray[3] == 2.0){
            model.b2.isSelected = true
            str = "long"
        }else if(line.strokeDashArray[3] == 30.0){
            model.b3.isSelected = true
            str = "dot"
        }else{
            model.b4.isSelected = true
            str = "dots"
        }
        return str
    }
    private fun unlock(){
        model.slider.isDisable = false
        model.colorPicker.isDisable = false
        model.colorPicker2.isDisable = false
        model.b1.isDisable = false
        model.b2.isDisable = false
        model.b3.isDisable = false
        model.b4.isDisable = false
    }
    private var state = STATE.NONE
    private fun layoutView() {}
    init {

        this.model.addView(this)
        val shape = Rectangle()
        shape.fill = Color.WHITE
        shape.height = 860.0
        shape.width = 1960.0
        model.pane1.children.addAll(shape)

        model.pane1.setOnMousePressed { event ->
            val line = Line()
            val rectangle = Rectangle()
            val circle = Ellipse()
            var flag = true
            circle.onMouseClicked = EventHandler {
                    if(model.selection == "select") {
                        flag = true
                        unlock()
                        circle.toFront()
                        //indicates property
                        model.slider.value = circle.strokeWidth
                        model.thickness = circle.strokeWidth.toString()
                        model.colorPicker.value = circle.stroke as Color?
                        if (circle.fill != Color.TRANSPARENT) {
                            model.colorPicker2.value = circle.fill as Color?
                        }
                        val my_str = select(circle)
                        dash(circle, my_str)
                        //modify
                        model.colorPicker.setOnAction {
                            if(model.selection == "select") {
                                circle.stroke = model.colorPicker.value
                            }
                        }
                        model.colorPicker2.setOnAction {
                            if(model.selection == "select"){
                                circle.fill = model.colorPicker2.value
                            }
                        }
                        model.slider.onMouseDragged = EventHandler{
                            if(model.selection == "select") {
                                circle.strokeWidth = model.slider.value
                            }
                        }
                        model.b1.setOnAction {
                            if(model.selection == "select") dash(circle, "straght")
                        }
                        model.b2.setOnAction {
                            if(model.selection == "select") dash(circle, "long")
                        }
                        model.b3.setOnAction {
                            if(model.selection == "select") dash(circle, "dot")
                        }
                        model.b4.setOnAction {
                            if(model.selection == "select") dash(circle, "dots")
                        }
                        //move
                        var startX = -1.0
                        var startY = -1.0
                        if(model.selection == "select") {
                            circle.setOnMousePressed { event ->
                                startX = event.x
                                startY = event.y
                            }

                            circle.setOnMouseDragged { event ->
                                if (model.selection == "select") {
                                    val dx = event.x - startX
                                    val dy = event.y - startY
                                    circle.centerX += dx
                                    circle.centerY += dy
                                    startX = event.x
                                    startY = event.y
                                }
                            }
                        }

                    }else if(model.selection == "fill") {
                        unlock()
                        circle.fill = model.colorPicker2.value
                    }else if(model.selection == "erase"){
                        unlock()
                        circle.isVisible = false
                    }else if(model.selection == "rotate"){
                        unlock()
                        var previousX = 0.0
                        var previousY = 0.0
                        var rotate = Rotate()
                        rotate.pivotX = circle.centerX
                        rotate.pivotY = circle.centerY
                        rotate.angle = 0.0
                        circle.setOnMousePressed { event->
                            previousX = event.x
                            previousY = event.y
                        }

                        circle.setOnMouseDragged { event->
                            val distance = Math.sqrt(
                                Math.pow(event.x - previousX, 2.0) + Math.pow(event.y - previousY, 2.0)
                            )
                            val theta = Math.atan(distance)
                            circle.rotate -= theta
                        }
                    }else if(model.selection == "scale"){
                        unlock()
                        circle.setOnMouseDragged { event ->
                            if(model.selection == "scale") {
                                if (max(
                                        abs(event.x - circle.centerX),
                                        abs(event.y - circle.centerY)
                                    ) > circle.radiusY
                                ) {
                                    circle.scaleY += 0.05
                                    circle.scaleX += 0.05
                                } else {
                                    circle.scaleY -= 0.05
                                    circle.scaleX -= 0.05
                                }
                            }
                        }
                    }
                }

            line.onMouseClicked = EventHandler {
                line.toFront()
                    if(model.selection == "select") {
                        state = STATE.SELECTION
                        unlock()
                        model.slider.value = line.strokeWidth
                        model.thickness = line.strokeWidth.toString()
                        model.colorPicker.value = line.stroke as Color?
                        val my_str = select(line)
                        dash(line, my_str)

                        model.colorPicker.setOnAction {
                            if(model.selection == "select") line.stroke = model.colorPicker.value
                        }
                        model.slider.onMouseDragged = EventHandler{
                            if(model.selection == "select") line.strokeWidth = model.slider.value
                        }
                        model.b1.setOnAction {
                            if(model.selection == "select") dash(line, "straght")
                        }
                        model.b2.setOnAction {
                            if(model.selection == "select") dash(line, "long")
                        }
                        model.b3.setOnAction {
                            if(model.selection == "select") dash(line, "dot")
                        }
                        model.b4.setOnAction {
                            if(model.selection == "select") dash(line, "dots")
                        }

                        var startX = -1.0
                        var startY = -1.0
                        line.setOnMousePressed { event ->
                            startX = event.x
                            startY = event.y
                        }

                        line.setOnMouseDragged { event ->
                            if (model.selection == "select") {
                                val dx = event.x - startX
                                val dy = event.y - startY
                                line.startX += dx
                                line.endX += dx
                                line.startY += dy
                                line.endY += dy
                                startX = event.x
                                startY = event.y
                            }
                        }

                    }else if(model. selection == "erase") {
                        unlock()
                        line.isVisible = false
                    }else if(model.selection == "rotate"){
                        unlock()
                        val previousX = event.x
                        val previousY = event.y

                        line.setOnMouseDragged { event->
                            val distance = Math.sqrt(
                                Math.pow(event.x - previousX, 2.0) + Math.pow(event.y - previousY, 2.0)
                            )
                            val theta = Math.atan(distance)
                            val rotation = line.rotate + theta
                            line.rotate = rotation
                        }

                    }else if(model.selection == "scale"){
                        unlock()
                        val len = sqrt(pow((line.endX - line.startX),2.0) + pow((line.endY - line.startY),2.0))
                        line.setOnMouseDragged { event ->
                            if(model.selection == "scale") {
                                if (max(
                                    abs(event.x - line.startX),
                                    abs(event.y - line.startY)
                                ) > len
                                ) {
                                    line.scaleY += 0.02
                                    line.scaleX += 0.02
                                } else {
                                    line.scaleY -= 0.02
                                    line.scaleX -= 0.02
                                }
                            }
                        }
                    }
                }

            rectangle.onMouseClicked = EventHandler {
                rectangle.toFront()
                val rx = rectangle.x
                val ry = rectangle.y
                if (model.selection == "select") {
                    unlock()
                    state = STATE.SELECTION
                    model.slider.value = rectangle.strokeWidth
                    model.thickness = rectangle.strokeWidth.toString()
                    model.colorPicker.value = rectangle.stroke as Color?
                    if (rectangle.fill != Color.TRANSPARENT) {
                        model.colorPicker2.value = rectangle.fill as Color?
                    }else{
                        model.colorPicker2.value = Color.WHITE
                    }
                    val my_str = select(rectangle)
                    dash(rectangle, my_str)


                    model.colorPicker.setOnAction {
                        if (model.selection == "select") {
                            rectangle.stroke = model.colorPicker.value
                        }
                    }
                    model.colorPicker2.setOnAction {
                        if (model.selection == "select") {
                            rectangle.fill = model.colorPicker2.value
                        }
                    }
                    model.slider.onMouseDragged = EventHandler {
                        if (model.selection == "select") {
                            rectangle.strokeWidth = model.slider.value
                        }
                    }
                    model.b1.setOnAction {
                        if (model.selection == "select") dash(rectangle, "straght")
                    }
                    model.b2.setOnAction {
                        if (model.selection == "select") dash(rectangle, "long")
                    }
                    model.b3.setOnAction {
                        if (model.selection == "select") dash(rectangle, "dot")
                    }
                    model.b4.setOnAction {
                        if (model.selection == "select") dash(rectangle, "dots")
                    }
                    if (model.selection == "select") {
                        var startX = -1.0
                        var startY = -1.0
                        rectangle.setOnMousePressed { event ->
                            startX = event.x
                            startY = event.y
                        }

                        rectangle.setOnMouseDragged { event ->
                            if (model.selection == "select") {
                                val dx = event.x - startX
                                val dy = event.y - startY
                                rectangle.x += dx
                                rectangle.y += dy
                                println(rectangle.x.toString())
                                println(rectangle.xProperty().value)
                                startX = event.x
                                startY = event.y
                            }
                        }
                    }

                } else if (model.selection == "fill") {
                    unlock()
                    rectangle.fill = model.colorPicker2.value
                } else if (model.selection == "erase") {
                    unlock()
                    rectangle.isVisible = false
                } else if (model.selection == "rotate") {
                    unlock()
                    var previousX = event.x
                    var previousY = event.y

                    rectangle.setOnMouseDragged { event ->
                        val distance = Math.sqrt(
                            Math.pow(event.x - rectangle.x, 2.0) + Math.pow(event.y - rectangle.y, 2.0)
                        )
                        val theta = Math.atan(distance)
                        rectangle.rotate += theta

                    }

                } else if (model.selection == "scale") {
                    unlock()
                    rectangle.setOnMouseDragged { event ->
                        if (model.selection == "scale") {
                            if (max(
                                    abs(event.x - rectangle.x),
                                    abs(event.y - rectangle.y)
                                ) > min(rectangle.x,rectangle.y)
                            ) {
                                rectangle.scaleY += 0.02
                                rectangle.scaleX += 0.02
                            } else {
                                rectangle.scaleY -= 0.02
                                rectangle.scaleX -= 0.02
                            }
                        }
                    }
                }
            }
            state = STATE.NONE
            model.slider.isDisable = true
            model.colorPicker.isDisable = true
            model.colorPicker2.isDisable = true
            model.b1.isDisable = true
            model.b2.isDisable = true
            model.b3.isDisable = true
            model.b4.isDisable = true
            model.pane1.setOnKeyPressed {event->
                if(event.code == KeyCode.ESCAPE){
                    println("yes")
                }
            }
            if (model.selection == "select" || model.selection == "erase"
                || model.selection == "scale"
                || model.selection == "rotate"||model.selection == "fill"){
                state = STATE.SELECTION
            }
                 if(model.selection == "line") {
                     model.slider.isDisable = false
                     model.colorPicker.isDisable = false
                     model.colorPicker2.isDisable = false
                     model.b1.isDisable = false
                     model.b2.isDisable = false
                     model.b3.isDisable = false
                     model.b4.isDisable = false
                     state = STATE.DRAWING
                     line.stroke = model.colorPicker.value
                     line.strokeWidth = model.slider.value
                     dash(line, model.dash)
                     line.strokeDashArray
                     line.startX = event.x
                     line.startY = event.y
                     line.endX = event.x
                     line.endY = event.y
                     model.pane1.setOnMouseDragged { event2 ->
                        if (state == STATE.DRAWING) {
                            line.endX = event2.x
                            line.endY = event2.y
                        }
                    }
                     model.pane1.children.addAll(line)
                }
                 else if (model.selection == "rectangle") {
                     model.slider.isDisable = false
                     model.colorPicker.isDisable = false
                     model.colorPicker2.isDisable = false
                     model.b1.isDisable = false
                     model.b2.isDisable = false
                     model.b3.isDisable = false
                     model.b4.isDisable = false
                     state = STATE.DRAWING
                     rectangle.stroke = model.colorPicker.value
                     rectangle.strokeWidth = model.slider.value
                     dash(rectangle, model.dash)
                    val x = event.x
                    val y = event.y
                    rectangle.x = event.x
                    rectangle.y = event.y
                    rectangle.width = 1.0
                    rectangle.height = 1.0
                    rectangle.fill = Color.WHITE
                     model.pane1.children.add(rectangle)
                     model.pane1.setOnMouseDragged { event ->
                        if (state == STATE.DRAWING) {
                            if (event.x >= x) {
                                rectangle.x = x
                                rectangle.width = event.x - rectangle.x
                            } else {
                                rectangle.x = event.x
                                rectangle.width = x - event.x
                            }
                            if (event.y >= y) {
                                rectangle.y = y
                                rectangle.height = event.y - rectangle.y
                            } else {
                                rectangle.y = event.y
                                rectangle.height = y - event.y
                            }
                        }
                    }
                }
                 else if (model.selection == "circle") {
                     model.slider.isDisable = false
                     model.colorPicker.isDisable = false
                     model.colorPicker2.isDisable = false
                     model.b1.isDisable = false
                     model.b2.isDisable = false
                     model.b3.isDisable = false
                     model.b4.isDisable = false
                     state = STATE.DRAWING
                     circle.stroke = model.colorPicker.value
                     circle.strokeWidth = model.slider.value
                     dash(circle, model.dash)
                    val x = event.x
                    val y = event.y
                    circle.centerX = event.x
                    circle.centerY = event.y
                    circle.radiusX = 0.0
                    circle.radiusY = 0.0
                    circle.fill = Color.WHITE
                     model.pane1.children.add(circle)
                     model.pane1.setOnMouseDragged { event ->
                        if (state == STATE.DRAWING) {
                            circle.centerX = x
                            circle.centerY = y
                            circle.radiusY = max(abs(event.x - circle.centerX) / 2, abs(event.y - circle.centerY) / 2)
                            circle.radiusX = circle.radiusY
                        }
                    }
                }

            }
        println(model.selection)
            this.children.addAll(model.pane1)

    }

    override fun update() {

        layoutView()
    }
}