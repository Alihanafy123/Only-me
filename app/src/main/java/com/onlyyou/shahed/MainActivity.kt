
package com.onlyyou.shahed

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {

    private val bgTop = Color.rgb(22, 12, 39)
    private val bgBottom = Color.rgb(5, 5, 14)
    private val pink = Color.rgb(255, 143, 205)
    private val lavender = Color.rgb(210, 185, 255)

    private lateinit var root: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showHome()
    }

    private fun gradient(): GradientDrawable =
        GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(bgTop, bgBottom)
        )

    private fun createRoot(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(dp(24), dp(28), dp(24), dp(24))
            background = gradient()
        }
    }

    private fun showHome() {
        root = createRoot()

        val scroll = ScrollView(this).apply {
            isFillViewport = true
            addView(root)
        }

        root.addView(
            text("✦     ·       ✧       ·     ✦", 19, lavender).apply {
                gravity = Gravity.CENTER
            }, matchWidth()
        )

        root.addView(space(34))

        root.addView(
            text("A LITTLE UNIVERSE", 11, lavender).apply {
                letterSpacing = 0.28f
                gravity = Gravity.CENTER
            }, matchWidth()
        )

        root.addView(space(12))

        root.addView(
            text("ONLY YOU", 38, Color.WHITE, true).apply {
                gravity = Gravity.CENTER
                letterSpacing = 0.12f
            }, matchWidth()
        )

        root.addView(space(8))

        root.addView(
            text("A world made for Shahed ♡", 16, pink).apply {
                gravity = Gravity.CENTER
            }, matchWidth()
        )

        root.addView(space(30))

        root.addView(
            text("♥", 76, pink, true).apply {
                gravity = Gravity.CENTER
            }, matchWidth()
        )

        root.addView(space(12))

        root.addView(
            text(
                "في وسط كل الحكايات…\nإنتِ الحكاية الأقرب لقلبي.",
                20,
                Color.WHITE
            ).apply {
                gravity = Gravity.CENTER
                setLineSpacing(dp(7).toFloat(), 1f)
            }, matchWidth()
        )

        root.addView(space(34))

        addAction("رسائل ليكي", "LETTERS") {
            showMessage(
                "رسائل ليكي",
                "كل يوم عندي سبب جديد أحبك عشانه.\n\n" +
                "وجودك في حياتي حكاية جميلة، " +
                "ولسه أجمل فصولها جاية. ♡"
            )
        }

        addAction("ذكرياتنا ❤️", "MEMORIES") {
            showMemories()
        }

        addAction("مفاجأة صغيرة", "SURPRISE") {
            showMessage(
                "مفاجأة صغيرة",
                "يا شهد، لو العالم كله اتغير، " +
                "هيفضل ليكي مكان مميز في حكايتي. ❤️"
            )
        }

        root.addView(space(28))

        root.addView(
            text("MADE WITH LOVE  •  JUST FOR YOU", 10, lavender).apply {
                gravity = Gravity.CENTER
                letterSpacing = 0.12f
            }, matchWidth()
        )

        setContentView(scroll)
    }

    // Memories Gallery

    private fun showMemories() {
        root = createRoot()

        val scroll = ScrollView(this).apply {
            isFillViewport = true
            addView(root)
        }

        root.addView(
            text("✦ OUR STORY ✦", 30, Color.WHITE, true).apply {
                gravity = Gravity.CENTER
                letterSpacing = 0.08f
            }, matchWidth()
        )

        root.addView(space(10))

        root.addView(
            text("كل صورة بتحكي حكاية من حكايتنا ❤️", 16, lavender).apply {
                gravity = Gravity.CENTER
            }, matchWidth()
        )

        root.addView(space(25))

        for (i in 1..10) {
            val imageName = "our_photo_%02d".format(i)

            addMemoryPhoto(
                imageName,
                "ذكرى رقم $i",
                "لحظة من حكايتنا الجميلة ❤️"
            )
        }

        root.addView(space(20))

        addAction("العودة لعالمنا", "BACK") {
            showHome()
        }

        setContentView(scroll)
    }

    private fun addMemoryPhoto(
        imageName: String,
        caption: String,
        description: String
    ) {
        val resourceId = resources.getIdentifier(
            imageName,
            "drawable",
            packageName
        )

        if (resourceId == 0) {
            root.addView(
                text("الصورة غير موجودة: $imageName", 13, pink),
                matchWidth()
            )
            root.addView(space(12))
            return
        }

        val card = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(dp(10), dp(10), dp(10), dp(14))

            background = GradientDrawable().apply {
                setColor(Color.argb(35, 255, 143, 205))
                cornerRadius = dp(18).toFloat()
                setStroke(dp(1), Color.argb(130, 255, 143, 205))
            }
        }

        val image = ImageView(this).apply {
            setImageResource(resourceId)
            scaleType = ImageView.ScaleType.CENTER_CROP
            contentDescription = caption
            background = GradientDrawable().apply {
                cornerRadius = dp(12).toFloat()
            }
            clipToOutline = true
        }

        card.addView(
            image,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dp(230)
            )
        )

        card.addView(space(12))

        card.addView(
            text(caption, 19, Color.WHITE, true).apply {
                gravity = Gravity.CENTER
            }, matchWidth()
        )

        card.addView(space(5))

        card.addView(
            text(description, 13, lavender).apply {
                gravity = Gravity.CENTER
            }, matchWidth()
        )

        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            bottomMargin = dp(20)
        }

        root.addView(card, params)
    }

    // Messages

    private fun showMessage(title: String, message: String) {
        root = createRoot()
        root.gravity = Gravity.CENTER

        root.addView(
            text("✦", 34, pink).apply {
                gravity = Gravity.CENTER
            }, matchWidth()
        )

        root.addView(space(18))

        root.addView(
            text(title, 29, Color.WHITE, true).apply {
                gravity = Gravity.CENTER
            }, matchWidth()
        )

        root.addView(space(22))

        root.addView(
            text(message, 19, lavender).apply {
                gravity = Gravity.CENTER
                setLineSpacing(dp(8).toFloat(), 1f)
            }, matchWidth()
        )

        root.addView(space(36))

        addAction("العودة لعالمنا", "BACK") {
            showHome()
        }

        setContentView(root)
    }

    // Buttons

    private fun addAction(
        label: String,
        tag: String,
        onClick: () -> Unit
    ) {
        val button = Button(this).apply {
            text = label
            textSize = 16f
            isAllCaps = false
            setTextColor(Color.WHITE)

            background = GradientDrawable().apply {
                setColor(Color.argb(34, 255, 143, 205))
                cornerRadius = dp(18).toFloat()
                setStroke(
                    dp(1),
                    Color.argb(150, 255, 143, 205)
                )
            }

            contentDescription = tag
            setOnClickListener { onClick() }
        }

        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            dp(56)
        ).apply {
            bottomMargin = dp(12)
        }

        root.addView(button, params)
    }

    // Helpers

    private fun text(
        value: String,
        size: Int,
        color: Int,
        bold: Boolean = false
    ) = TextView(this).apply {
        text = value
        textSize = size.toFloat()
        setTextColor(color)

        typeface = if (bold)
            Typeface.create("sans-serif", Typeface.BOLD)
        else
            Typeface.create("sans-serif", Typeface.NORMAL)
    }

    private fun space(height: Int) = View(this).apply {
        layoutParams = LinearLayout.LayoutParams(
            1,
            dp(height)
        )
    }

    private fun matchWidth() =
        LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

    private fun dp(value: Int): Int =
        (value * resources.displayMetrics.density).toInt()
}
