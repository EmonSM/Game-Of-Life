import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.Region

class StatusView(model: Model) : IView, HBox() {
    private val lastAction = Label(" New simulation started")
    private val currentShape = Label("Please select a shape")
    private val frameCount = Label("Frame ${model.frameNumber} ")

    init {
        val blankRegion1 = Region()
        setHgrow(blankRegion1, Priority.ALWAYS)
        val blankRegion2 = Region()
        setHgrow(blankRegion2, Priority.ALWAYS)

        this.children.addAll(lastAction, blankRegion1, currentShape, blankRegion2, frameCount)
    }

    override fun update(model: Model) {
        lastAction.text = " ${model.lastAction}"
        currentShape.text = if (model.currentShape == "Please select a shape") {
            model.currentShape
        } else {
            "Current shape: ${model.currentShape}"
        }
        frameCount.text = "Frame ${model.frameNumber} "
    }
}