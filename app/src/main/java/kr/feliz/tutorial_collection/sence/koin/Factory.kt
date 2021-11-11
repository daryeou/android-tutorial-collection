package kr.feliz.tutorial_collection.sence.koin

class Factory(val description: String) {
    @JvmName("getName1")
    fun getName(subName: String?): Student{
        return Student("$description $subName")
    }
}