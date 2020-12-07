package ru.spider.imgurclient.di

import dagger.Component
import ru.spider.imgurclient.view.viewModels.GalleryViewModel
import ru.spider.imgurclient.view.viewModels.ImageDetailViewModel
import javax.inject.Singleton

@Component(modules = [AndroidModule::class])
@Singleton
interface AppComponent {
    fun inject(galleryViewModel: GalleryViewModel)
    fun inject(imageDetailViewModel: ImageDetailViewModel)
}