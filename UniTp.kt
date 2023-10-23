package tps

// Classe University
class University(private val name: String, private val foundationYear: Int) {
    private val professors = mutableListOf<Professor>()
    private val students = mutableListOf<Student>()

    fun recruitProfessor(professor: Professor) {
        professors.add(professor)
        println("Professor ${professor.name} recruited at $name")
    }

    fun enrollStudent(student: Student) {
        students.add(student)
        println("Student ${student.name} enrolled at $name")
    }
}

// Classe Professor
class Professor(var name: String,var age: Int,var salary: Int) {
    fun teach(course: Course) {
        println("Professor $name is teaching ${course.name}")
    }

    fun doOralExam(student: Student, course: Course): Result {
        var result = SuccessResult()
        println("Professor $name is conducting an oral exam for ${student.name} on ${course.name}")
        return result
    }
}

// Classe Student
class Student(var name: String,var age: Int,var studentId: Int) {
    private val courses = mutableListOf<Course>()
    private var successProbability = 50

    fun enroll(course: Course) {
        courses.add(course)
        println("Student $name enrolled in ${course.name}")
    }

    fun takeExam(course: Course) {
        var result = SuccessResult()
        println("Student $name took an exam on ${course.name}")
        grade(course, result)
    }

    fun learn() {
        successProbability += 2
        println("Student $name is learning")
    }

    fun party() {
        successProbability -= 2
        println("Student $name is partying")
    }

    private fun grade(course: Course, result: Result) {
        if (result.success) {
            println("Student $name passed the exam on ${course.name}")
        } else {
            println("Student $name failed the exam on ${course.name}")
        }
    }
}

// Classe Course
data class Course(var name: String)

// Classe Result
sealed class Result(var success: Boolean)

class SuccessResult : Result(true)
class FailureResult : Result(false)

fun main() {
    val university = University("Ntic", 1990)
    val professor = Professor("Atik", 40, 50000)
    val student = Student("Yahya", 20, 12345)
    val course = Course("Kotlin")

    university.recruitProfessor(professor)
    university.enrollStudent(student)

    professor.teach(course)
    student.enroll(course)

    student.party()
    student.party()
    student.party()
    student.learn()

    student.takeExam(course)
    professor.doOralExam(student, course)
}