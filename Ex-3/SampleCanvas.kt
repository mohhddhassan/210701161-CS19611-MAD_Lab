age org.rajalakshmi.graphicalprimitives

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class SampleCanvas @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val paint : Paint = Paint()
        paint.setColor(Color.YELLOW)
        canvas.drawPaint(paint)
        paint.setColor(Color.RED)
        paint.setTextSize(50f)
        canvas?.drawText("Circle", 100f , 100f , paint)
        canvas?.drawCircle(200f , 250f , 100f , paint)
        paint.setColor(Color.GREEN)
        paint.setTextSize(50f)
        canvas?.drawText("Rectangle" , 600f , 100f , paint)
        canvas?.drawRect(800f , 150f , 600f , 500f , paint)
        paint.setColor(Color.BLUE)
        paint.setTextSize(50f)
        canvas?.drawText("Square" , 110f , 650f , paint)
        canvas?.drawRect(100f , 700f , 300f , 900f , paint)
        canvas?.drawText("Line" , 600f , 650f , paint)
        canvas?.drawLine(900f , 700f , 300f , 400f, paint)
