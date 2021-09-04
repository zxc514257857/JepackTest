package com.zhr.mvp2

import kotlin.random.Random

class PlayerModel {

    fun requestSongs(): MutableList<SongsBean> {
        val songsList: MutableList<SongsBean> = mutableListOf()
        val songsBean1 = SongsBean()
        songsBean1.title = "STAY"
        songsBean1.pics = R.mipmap.pic1
        songsList.add(songsBean1)

        val songsBean2 = SongsBean()
        songsBean2.title = "初恋"
        songsBean2.pics = R.mipmap.pic2
        songsList.add(songsBean2)

        val songsBean3 = SongsBean()
        songsBean3.title = "还是会想你"
        songsBean3.pics = R.mipmap.pic3
        songsList.add(songsBean3)

        val songsBean4 = SongsBean()
        songsBean4.title = "晚风"
        songsBean4.pics = R.mipmap.pic4
        songsList.add(songsBean4)

        val songsBean5 = SongsBean()
        songsBean5.title = "起风了"
        songsBean5.pics = R.mipmap.pic5
        songsList.add(songsBean5)

        val songsBean6 = SongsBean()
        songsBean6.title = "雨爱"
        songsBean6.pics = R.mipmap.pic6
        songsList.add(songsBean6)

        val songsBean7 = SongsBean()
        songsBean7.title = "天上飞"
        songsBean7.pics = R.mipmap.pic7
        songsList.add(songsBean7)

        val songsBean8 = SongsBean()
        songsBean8.title = "哪里都是你"
        songsBean8.pics = R.mipmap.pic8
        songsList.add(songsBean8)

        val songsBean9 = SongsBean()
        songsBean9.title = "错位时空"
        songsBean9.pics = R.mipmap.pic9
        songsList.add(songsBean9)

        val songsBean10 = SongsBean()
        songsBean10.title = "水星记"
        songsBean10.pics = R.mipmap.pic10
        songsList.add(songsBean10)

        return songsList
    }

    fun getSongById(): SongsBean {
        val songs = requestSongs()
        return songs[Random.nextInt(songs.size)]
    }
}