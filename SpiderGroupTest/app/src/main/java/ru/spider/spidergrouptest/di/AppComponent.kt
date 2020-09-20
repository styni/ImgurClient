package ru.spider.spidergrouptest.di

import dagger.Component
import ru.spider.spidergrouptest.view.dataSource.GalleryImagesRepository
import ru.spider.spidergrouptest.view.viewModels.GalleryViewModel
import ru.spider.spidergrouptest.view.viewModels.ImageDetailViewModel
import javax.inject.Singleton

@Component(modules = [AndroidModule::class])
@Singleton
interface AppComponent {
    fun inject(galleryViewModel: GalleryViewModel)
    fun inject(imageDetailViewModel: ImageDetailViewModel)
}