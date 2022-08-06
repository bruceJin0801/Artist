import javafx.scene.control.ColorPicker
import javafx.scene.control.RadioButton
import javafx.scene.control.Slider
import javafx.scene.control.ToggleGroup
import javafx.scene.layout.Pane
import javafx.scene.shape.Line
import java.awt.Color
import java.awt.Paint

class Model {
    private val views: ArrayList<IView> = ArrayList()
    var selection = "";
    var dash = "straight"
    var tg = ToggleGroup()
    var b1 = RadioButton()
    var b2 = RadioButton()
    var b3 = RadioButton()
    var b4 = RadioButton()
    var fill = javafx.scene.paint.Color.GREEN
    var thickness = "3.0"
    var Isselected = false
    var colorPicker = ColorPicker()
    var colorPicker2 = ColorPicker()
    val slider = Slider()
    var pane1 = Pane()
    fun addView(view: IView) {
        views.add(view)
        view.update()
    }

    fun removeView(view: IView) {
        views.remove(view)
    }




     fun notifyObservers() {
        for (view in views) {
            view.update()
        }
    }

}