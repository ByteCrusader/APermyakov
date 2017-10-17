package ru.apermyakov;

/**
 * Class for create Teacher.
 *
 * @author apermyakov
 * @version 1.0
 * @since 13.10.2017
 */
public class Teacher extends Profession {

	/**
	* Feild English knowledge.
	*/
    public boolean upperIntermediateEnglish;

	/**
	* Feild Number of published articles.
	*/
    public int numberOfPublishedArticles;

	/**
	* Feild Number of additional training courses.
	*/
    public int numberOfAdditionalTrainingCourses;

	/**
	* Design Teacher.
	*
	* @param name Name of Teacher
	* @param upperIntermediateEnglish is teacher have english knowledge
	* @param numberOfPublishedArticles number of teacher's published articles
	* @param numberOfAdditionalTrainingCourses number of teacher's additional training courses
	* @param universityDiplom is teacher have university diplom
	*/
    public Teacher(String name, boolean upperIntermediateEnglish, int numberOfPublishedArticles, boolean universityDiplom, int numberOfAdditionalTrainingCourses) {
        this.name = name;
        this.upperIntermediateEnglish = upperIntermediateEnglish;
        this.numberOfPublishedArticles = numberOfPublishedArticles;
        this.universityDiplom = universityDiplom;
        this.numberOfAdditionalTrainingCourses = numberOfAdditionalTrainingCourses;
    }

	/**
	* Class for create teacher's action.
	*
	* @author apermyakov
	* @version 1.0
	* @since 17.10.2017
	*/
	public class TeacherAction {
		/**
		* Feild name of the teacher.
		*/
		public String teacherName;
		/**
		* Feild name of the student.
		*/
		public String studentName;

		/**
		* Base Designer.
		*/
		public TeacherAction() {
		}

		/**
		* Design TeacherAction.
		*
		* @param teacherName name of the teacher
		* @param studentName name of the student
		*/
		public TeacherAction(String teacherName, String studentName) {
			this.teacherName = teacherName;
			this.studentName = studentName;
		}

		/**
		* Method get name of the teacher.
		* @return name of the teacher
		*/
		public String getTeacherName() {
			return this.teacherName;
		}

		/**
		* Method get name of the student.
		* @return name of the student
		*/
		public String getStudentName() {
			return this.studentName;
		}
	}

	/** Class for create student.
	*
	* @author apermyakov
	* @version 1.0
	* @since 17.10.2017
	*/
	public class Student extends TeacherAction {
	}

	/**
	* Class for create student's knowledge.
	*
	* @author apermyakov
	* @version 1.0
	* @since 17.10.2017
	*/
	public class Knowledge extends TeacherAction {
		/**
		* Feild chapter of the textbook.
		*/
		public int textbookChapter;

		/**
		* Design Knowledge.
		*
		* @param teacherName name of the teacher
		* @param studentName name of the student
		* @param textbookChapter chapter of the textbook
		*/
		public Knowledge(String teacherName, String studentName, int textbookChapter) {
			this.teacherName = teacherName;
			this.studentName = studentName;
			this.textbookChapter = textbookChapter;
		}

		/**
		* Method get chapter of the textbook.
		* @return chapter of the textbook
		*/
		public int getTextbookChapter() {
			return this.textbookChapter;
		}
	}

	/**
	* Method teacher teach student.
	* @param student name of student
	* @return Knowledge of student
	*/
    public Knowledge teach(Student student) {
        return new Knowledge(this.name, student.getStudentName(), 2);
    }
	/*
	* Example for work with this method:
	* Teacher senior = new Teacher("Petr", true, 100500, true, 100500);
	* Student junior = new Student("Petr", "Alexander");
	* Knowledge course = senior.teach(junior);
	* System.out.println(String.format("Teacher %s teach %s", course.getTeacherName(), course.getStudentName()));
	*/

	/**
	* Method teacher training another teacher.
	* @param teacher another teacher
	* @return Knowledge of teacher
	*/
    public Knowledge training(Teacher teacher) {
        return new Knowledge(this.name, teacher.getName(), 15);
    }
	/*
	* Example for work with this method:
	* Teacher head = new Teacher("Inna", true, 25, true, 10);
	* Student middle = new Student("Inna", "Vasilii");
	* Knowledge lesson = head.training(middle);
	* System.out.println(String.format("Teacher %s training teacher %s", lesson.getTeacherName(), lesson.getStudentName()));
	*/
}
