package com.example.authorization

import com.bumptech.glide.Glide
import android.os.Bundle
import android.os.AsyncTask
import androidx.activity.enableEdgeToEdge
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Button
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Создаем список из ImageView для Login
        val imageViewsForLogin = listOf(
            findViewById<ImageView>(R.id.image_one_log),
            findViewById<ImageView>(R.id.image_two_log),
            findViewById<ImageView>(R.id.image_three_log),
            findViewById<ImageView>(R.id.image_four_log)
        )

        // Создаем список из ImageView для Password
        val imageViewsForPassword = listOf(
            findViewById<ImageView>(R.id.image_one_password),
            findViewById<ImageView>(R.id.image_two_password),
            findViewById<ImageView>(R.id.image_three_password),
            findViewById<ImageView>(R.id.image_four_password)
        )

        //Подгружаем картинки с сервера
        var pictos: Array<Pictogram> = arrayOf()
        var auth = Authorization("ait2-vladislav001.amvera.io")
        auth.getPictograms{
            val pictos_ = unwrapPictograms( it )
            if ( pictos_ != null ) {
                pictos = pictos_
                val pictos_ = pictos_

                var images = arrayOf(
                    R.id.img_button_1, R.id.img_button_2,
                    R.id.img_button_3, R.id.img_button_4,
                    R.id.img_button_5, R.id.img_button_6,
                    R.id.img_button_7, R.id.img_button_8,
                    R.id.img_button_9, R.id.img_button_10,
                    R.id.img_button_11, R.id.img_button_12,
                    R.id.img_button_13, R.id.img_button_14,
                    R.id.img_button_15, R.id.img_button_16
                )

                var images2 = arrayOf(
                    R.id.img_button_1_2, R.id.img_button_2_2,
                    R.id.img_button_3_2, R.id.img_button_4_2,
                    R.id.img_button_5_2, R.id.img_button_6_2,
                    R.id.img_button_7_2, R.id.img_button_8_2,
                    R.id.img_button_9_2, R.id.img_button_10_2,
                    R.id.img_button_11_2, R.id.img_button_12_2,
                    R.id.img_button_13_2, R.id.img_button_14_2,
                    R.id.img_button_15_2, R.id.img_button_16_2
                )

                for ( i in 0..images.size - 1) {
                    val imageUrl = "https://" + pictos[i].image
                    var imgbtn = findViewById<ImageButton>( images[i] )
                    Glide.with(this).load(imageUrl).into( imgbtn )
                    imgbtn.tag = imageUrl
                }

                for ( i in 0..images2.size - 1 ) {
                    val imageUrl = "https://" + pictos[i].image
                    var imgbtn = findViewById<ImageButton>( images2[i] )
                    Glide.with(this).load(imageUrl).into( imgbtn )
                    imgbtn.tag = imageUrl
                }
            }
        }

        //Находим кнопки
        val authButton = findViewById<Button>(R.id.authorization)
        val imgButtonOne = findViewById<ImageButton>(R.id.img_button_1)
        val imgButtonTwo = findViewById<ImageButton>(R.id.img_button_2)
        val imgButtonThree = findViewById<ImageButton>(R.id.img_button_3)
        val imgButtonFour = findViewById<ImageButton>(R.id.img_button_4)
        val imgButtonFive = findViewById<ImageButton>(R.id.img_button_5)
        val imgButtonSix = findViewById<ImageButton>(R.id.img_button_6)
        val imgButtonSeven = findViewById<ImageButton>(R.id.img_button_7)
        val imgButtonEight = findViewById<ImageButton>(R.id.img_button_8)
        val imgButtonNine = findViewById<ImageButton>(R.id.img_button_9)
        val imgButtonTen = findViewById<ImageButton>(R.id.img_button_10)
        val imgButtonEleven = findViewById<ImageButton>(R.id.img_button_11)
        val imgButtonTwelve = findViewById<ImageButton>(R.id.img_button_12)
        val imgButtonThirteen = findViewById<ImageButton>(R.id.img_button_13)
        val imgButtonFourteen = findViewById<ImageButton>(R.id.img_button_14)
        val imgButtonFiveteen = findViewById<ImageButton>(R.id.img_button_15)
        val imgButtonSixteen = findViewById<ImageButton>(R.id.img_button_16)
        val cancelButton = findViewById<ImageButton>(R.id.cancel_log)

        //Находим все необходимые кнопки для ввода пароля
        val imgButtonOnePassword = findViewById<ImageButton>(R.id.img_button_1_2)
        val imgButtonTwoPassword = findViewById<ImageButton>(R.id.img_button_2_2)
        val imgButtonThreePassword = findViewById<ImageButton>(R.id.img_button_3_2)
        val imgButtonFourPassword = findViewById<ImageButton>(R.id.img_button_4_2)
        val imgButtonFivePassword = findViewById<ImageButton>(R.id.img_button_5_2)
        val imgButtonSixPassword = findViewById<ImageButton>(R.id.img_button_6_2)
        val imgButtonSevenPassword = findViewById<ImageButton>(R.id.img_button_7_2)
        val imgButtonEightPassword = findViewById<ImageButton>(R.id.img_button_8_2)
        val imgButtonNinePassword = findViewById<ImageButton>(R.id.img_button_9_2)
        val imgButtonTenPassword = findViewById<ImageButton>(R.id.img_button_10_2)
        val imgButtonElevenPassword = findViewById<ImageButton>(R.id.img_button_11_2)
        val imgButtonTwelvePassword = findViewById<ImageButton>(R.id.img_button_12_2)
        val imgButtonThirteenPassword = findViewById<ImageButton>(R.id.img_button_13_2)
        val imgButtonFourteenPassword = findViewById<ImageButton>(R.id.img_button_14_2)
        val imgButtonFiveteenPassword = findViewById<ImageButton>(R.id.img_button_15_2)
        val imgButtonSixteenPassword = findViewById<ImageButton>(R.id.img_button_16_2)
        val cancelButtonPassword = findViewById<ImageButton>(R.id.cancel_password)

        // Создаем переменные для отслеживания текущего индекса
        var currentIndex_login = 0
        var currentIndex_password = 0

        // Создаем списки для хранения введенных значений
        val enteredValues_login = mutableListOf<String>()
        val enteredValues_password = mutableListOf<String>()

        fun getValue( imageSrc: String ): String? {
            for ( p in pictos ) {
                if ( "https://" + p.image == imageSrc ) {
                    return p.value
                }
            }
            return null
        }

        // Функция для обработки нажатия на кнопку ввода в поле логина
        fun onButtonClickedLogin(button: ImageButton) {
            // Если все ImageView уже заполнены, ничего не делаем
            if (currentIndex_login >= imageViewsForLogin.size) {
                return
            }
            // Иначе устанавливаем картинку
            val drawable = button.drawable
            imageViewsForLogin[currentIndex_login].setImageDrawable(drawable)

            // Получаем URL изображения из тега кнопки
            val imageUrl = button.tag as String

            var value = getValue(imageUrl)
            if (value != null) {
                enteredValues_login.add(value)
            }

            // Увеличиваем индекс
            currentIndex_login++
        }

        // Функция для обработки нажатия на кнопку ввода в поле пароля
        fun onButtonClickedPassword(button: ImageButton) {
            // Если все ImageView уже заполнены, ничего не делаем
            if (currentIndex_password >= imageViewsForPassword.size) {
                return
            }
            // Иначе устанавливаем картинку
            val drawable = button.drawable
            imageViewsForPassword[currentIndex_password].setImageDrawable(drawable)

            // Получаем URL изображения из тега кнопки
            val imageUrl = button.tag as String

            var value = getValue(imageUrl)
            if (value != null) {
                enteredValues_password.add(value)
            }
            // Увеличиваем индекс
            currentIndex_password++
        }

        // Функция для обработки нажатия на кнопку отмены в поле логина
        fun onCancelClickedLogin() {
            // Если еще ничего не вводилось, ничего не делаем
            if (currentIndex_login <= 0) {
                return
            }

            // Удаляем последнее введенное значение
            if (enteredValues_login.isNotEmpty()) {
                enteredValues_login.removeAt(enteredValues_login.size - 1)
            }

            // Удаляем картинку последней нажатой кнопки
            currentIndex_login--
            imageViewsForLogin[currentIndex_login].setImageDrawable(null)
        }

        // Функция для обработки нажатия на кнопку отмены в поле пароля
        fun onCancelClickedPassword() {
            // Если еще ничего не вводилось, ничего не делаем
            if (currentIndex_password <= 0) {
                return
            }

            // Удаляем последнее введенное значение
            if (enteredValues_password.isNotEmpty()) {
                enteredValues_password.removeAt(enteredValues_password.size - 1)
            }

            // Удаляем картинку последней нажатой кнопки
            currentIndex_password--
            imageViewsForPassword[currentIndex_password].setImageDrawable(null)
        }

        // Функция для вывода введенных значений логина и пароля в консоль
        fun printEnteredValues() {
            val loginString = enteredValues_login.joinToString("")
            val passwordString = enteredValues_password.joinToString("")
            auth.getToken( loginString, passwordString ) {
                val tkn = unwrapToken(it)
                if ( tkn != null ) {
                    val token = tkn!!
                    println("[INFO] Token: ${tkn}")
                    auth.getUserInfo(token) {
                        val userinfo = unwrapUserInfo( it )
                        if (userinfo != null) {
                            println("[INFO] UserInfo: ${userinfo?.name}, ${userinfo?.age}, ${userinfo?.gender}")
                        }
                    }
                }
            }
        }

        // Добавляем обработчик событий нажатия на кнопки отмены
        cancelButton.setOnClickListener { onCancelClickedLogin() }
        cancelButtonPassword.setOnClickListener { onCancelClickedPassword() }

        // Добавляем обработчики событий нажатия на кнопки в поле логина
        imgButtonOne.setOnClickListener { onButtonClickedLogin(imgButtonOne) }
        imgButtonTwo.setOnClickListener { onButtonClickedLogin(imgButtonTwo) }
        imgButtonThree.setOnClickListener { onButtonClickedLogin(imgButtonThree) }
        imgButtonFour.setOnClickListener { onButtonClickedLogin(imgButtonFour) }
        imgButtonFive.setOnClickListener { onButtonClickedLogin(imgButtonFive) }
        imgButtonSix.setOnClickListener { onButtonClickedLogin(imgButtonSix) }
        imgButtonSeven.setOnClickListener { onButtonClickedLogin(imgButtonSeven) }
        imgButtonEight.setOnClickListener { onButtonClickedLogin(imgButtonEight) }
        imgButtonNine.setOnClickListener { onButtonClickedLogin(imgButtonNine) }
        imgButtonTen.setOnClickListener { onButtonClickedLogin(imgButtonTen) }
        imgButtonEleven.setOnClickListener { onButtonClickedLogin(imgButtonEleven) }
        imgButtonTwelve.setOnClickListener { onButtonClickedLogin(imgButtonTwelve) }
        imgButtonThirteen.setOnClickListener { onButtonClickedLogin(imgButtonThirteen) }
        imgButtonFourteen.setOnClickListener { onButtonClickedLogin(imgButtonFourteen) }
        imgButtonFiveteen.setOnClickListener { onButtonClickedLogin(imgButtonFiveteen) }
        imgButtonSixteen.setOnClickListener { onButtonClickedLogin(imgButtonSixteen) }

        // Добавляем обработчики событий нажатия на кнопки в поле пароля
        imgButtonOnePassword.setOnClickListener { onButtonClickedPassword(imgButtonOnePassword) }
        imgButtonTwoPassword.setOnClickListener { onButtonClickedPassword(imgButtonTwoPassword) }
        imgButtonThreePassword.setOnClickListener { onButtonClickedPassword(imgButtonThreePassword) }
        imgButtonFourPassword.setOnClickListener { onButtonClickedPassword(imgButtonFourPassword) }
        imgButtonFivePassword.setOnClickListener { onButtonClickedPassword(imgButtonFivePassword) }
        imgButtonSixPassword.setOnClickListener { onButtonClickedPassword(imgButtonSixPassword) }
        imgButtonSevenPassword.setOnClickListener { onButtonClickedPassword(imgButtonSevenPassword) }
        imgButtonEightPassword.setOnClickListener { onButtonClickedPassword(imgButtonEightPassword) }
        imgButtonNinePassword.setOnClickListener { onButtonClickedPassword(imgButtonNinePassword) }
        imgButtonTenPassword.setOnClickListener { onButtonClickedPassword(imgButtonTenPassword) }
        imgButtonElevenPassword.setOnClickListener { onButtonClickedPassword(imgButtonElevenPassword) }
        imgButtonTwelvePassword.setOnClickListener { onButtonClickedPassword(imgButtonTwelvePassword) }
        imgButtonThirteenPassword.setOnClickListener { onButtonClickedPassword(imgButtonThirteenPassword) }
        imgButtonFourteenPassword.setOnClickListener { onButtonClickedPassword(imgButtonFourteenPassword) }
        imgButtonFiveteenPassword.setOnClickListener { onButtonClickedPassword(imgButtonFiveteenPassword) }
        imgButtonSixteenPassword.setOnClickListener { onButtonClickedPassword(imgButtonSixteenPassword) }

        // Добавляем обработчик событий нажатия на кнопку авторизации
        authButton.setOnClickListener { printEnteredValues() }
    }
}

