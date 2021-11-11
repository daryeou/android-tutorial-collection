package kr.feliz.tutorial_collection.sence.koin

class Teacher(var name: String, val teachingStudent: Student? = null) {
    init {
        if (teachingStudent != null) {
            this.name = (name+" "+teachingStudent.name)
        }
    }
}