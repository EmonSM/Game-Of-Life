class Model {
    private val views = ArrayList<IView>()
    // Status variables
    var lastAction = "New simulation started"
    var frameNumber = 1
    var currentShape = "Please select a shape"

    // Represents two-dimensional grid of square cells
    val numOfRows = 50
    val numOfCols = 75
    // Two boards are needed because all cells are updated at the same time,
    // so the previous board is needed to calculate the current board
    private val previousBoard = Array(numOfRows) { Array(numOfCols) {false} }
    val currentBoard = Array(numOfRows) { Array(numOfCols) {false} }

    fun setShape(newShape: String) {
        if (currentShape != newShape) {
            currentShape = newShape
            notifyViews()
        }
    }

    // Board Manipulation Methods:

    private fun makeCellLive(row: Int, col: Int) {
        // Checks if row and col are within the grid
        if (row in 0 until numOfRows && col in 0 until numOfCols) {
            currentBoard[row][col] = true
        }
    }

    fun addShape(row: Int, col: Int) {
        when (currentShape) {
            "Block" -> {
                makeCellLive(row, col)
                makeCellLive(row, col+1)
                makeCellLive(row+1, col)
                makeCellLive(row+1, col+1)
                lastAction = "Created block at $row, $col"
            }
            "Beehive" -> {
                makeCellLive(row, col+1)
                makeCellLive(row, col+2)
                makeCellLive(row+1, col)
                makeCellLive(row+1, col+3)
                makeCellLive(row+2, col+1)
                makeCellLive(row+2, col+2)
                lastAction = "Created beehive at $row, $col"
            }
            "Blinker" -> {
                makeCellLive(row, col)
                makeCellLive(row, col+1)
                makeCellLive(row, col+2)
                lastAction = "Created blinker at $row, $col"
            }
            "Toad" -> {
                makeCellLive(row, col+1)
                makeCellLive(row, col+2)
                makeCellLive(row, col+3)
                makeCellLive(row+1, col)
                makeCellLive(row+1, col+1)
                makeCellLive(row+1, col+2)
                lastAction = "Created toad at $row, $col"
            }
            "Glider" -> {
                makeCellLive(row, col+2)
                makeCellLive(row+1, col)
                makeCellLive(row+1, col+2)
                makeCellLive(row+2, col+1)
                makeCellLive(row+2, col+2)
                lastAction = "Created glider at $row, $col"
            }
            "Cell" -> {
                currentBoard[row][col] = !currentBoard[row][col]
                if (currentBoard[row][col]) lastAction = "Drew cell at $row, $col"
                else if (!currentBoard[row][col]) lastAction = "Erased cell at $row, $col"
            }
        }
        if (currentShape != "Please select a shape") notifyViews()
    }

    fun clearCurrentBoard() {
        for(row in currentBoard.indices) {
            for(col in currentBoard[row].indices) {
                currentBoard[row][col] = false
            }
        }
        lastAction = "Cleared grid"
        notifyViews()
    }

    private fun checkIfCellIsLive(row: Int, col: Int): Boolean {
        // Checks if row and col are within the grid
        if (row in 0 until numOfRows && col in 0 until numOfCols) {
            return previousBoard[row][col]
        }
        return false
    }

    private fun calculateNumOfLiveNeighbours(row: Int, col: Int): Int {
        var numOfLiveNeighbours = 0
        if (checkIfCellIsLive(row-1, col-1)) numOfLiveNeighbours++
        if (checkIfCellIsLive(row-1, col)) numOfLiveNeighbours++
        if (checkIfCellIsLive(row-1, col+1)) numOfLiveNeighbours++
        if (checkIfCellIsLive(row, col-1)) numOfLiveNeighbours++
        if (checkIfCellIsLive(row, col+1)) numOfLiveNeighbours++
        if (checkIfCellIsLive(row+1, col-1)) numOfLiveNeighbours++
        if (checkIfCellIsLive(row+1, col)) numOfLiveNeighbours++
        if (checkIfCellIsLive(row+1, col+1)) numOfLiveNeighbours++
        return numOfLiveNeighbours
    }

    fun updateBoards() {
        for(row in previousBoard.indices) {
            for(col in previousBoard[row].indices) {
                previousBoard[row][col] = currentBoard[row][col]
            }
        }
        for(row in currentBoard.indices) {
            for(col in currentBoard[row].indices) {
                val numOfLiveNeighbours = calculateNumOfLiveNeighbours(row, col)
                if (previousBoard[row][col] && numOfLiveNeighbours < 2) currentBoard[row][col] = false
                else if (previousBoard[row][col] && numOfLiveNeighbours <= 3) currentBoard[row][col] = true
                else if (previousBoard[row][col] && numOfLiveNeighbours > 3) currentBoard[row][col] = false
                else if (!previousBoard[row][col] && numOfLiveNeighbours == 3) currentBoard[row][col] = true
            }
        }
        frameNumber++
        notifyViews()
    }

    // View Management Methods:

    fun addView(view: IView) {
        views.add(view)
    }

//    fun removeView(view: IView) {
//        views.remove(view)
//    }

    private fun notifyViews() {
        for (view in views) {
            view.update(this)
        }
    }
}