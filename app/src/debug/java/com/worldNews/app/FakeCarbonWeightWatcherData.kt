package com.worldNews.app

import com.worldNews.app.data.model.Article


object FakeCarbonWeightWatcherData {

    const val FAKE_NETWORK_DELAY = 1000L

    //"author": "Melissa Healy, Amina Khan",
    //"title": "Big gap between Pfizer, Moderna vaccines seen for preventing COVID hospitalizations - Yahoo News",
    //"description": "Moderna's COVID-19 vaccine does a significantly better job of preventing COVID-19 hospitalizations compared with Pfizer's shot.",
    //"url": "https://news.yahoo.com/big-gap-between-pfizer-moderna-034719881.html",
    //"urlToImage": "https://s.yimg.com/uu/api/res/1.2/kPI1yr8MMPvvX3bc60FcCg--~B/aD01NjA7dz04NDA7YXBwaWQ9eXRhY2h5b24-/https://media.zenfs.com/en/la_times_articles_853/388fea30daa15c751c0ce15aac860846",
    //"publishedAt": "2021-09-18T04:38:34Z",
    //"content": "A dose of Pfizer-BioNTech COVID-19 vaccine is readied at a mobile vaccine clinic in Los Angeles. (Irfan Khan / Los Angeles Times) Amid persistent concerns that the protection offered by COVID-19 vac… [+8162 chars]"
    val carbonWeightWatcher = listOf(
        Article(
            id = 1,
            title = "muraino",
             description = "http",
            url = "snacks",
            publishedAt="publishedAt",
            content="content",
            urlToImage="urlToImage",
            author = "author"
        ),
        Article(
            id = 2,
            title = "muraino",
            description = "http",
            url = "snacks",
            publishedAt="publishedAt",
            content="content",
            urlToImage="urlToImage",
            author = "author"
        )

    )
}