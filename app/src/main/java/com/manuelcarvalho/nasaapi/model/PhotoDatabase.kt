package com.manuelcarvalho.nasaapi.model

//@Database(entities = arrayOf(Photo1::class), version = 1)
//abstract class PhotoDatabase : RoomDatabase() {
//    abstract fun photoDao(): PhotoDao
//
//    companion object {
//        @Volatile
//        private var instance: PhotoDatabase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance ?: buildDatabase(context).also {
//                instance = it
//            }
//        }
//
//        private fun buildDatabase(context: Context) = Room.databaseBuilder(
//            context.applicationContext,
//            PhotoDatabase::class.java,
//            "photos"
//        ).build()
//    }
//}