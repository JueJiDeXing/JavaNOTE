use s_t;
Select student.Sno, student.Sname, sc.Cno
From student
      right   Join sc on student.Sno = sc.Sno    ;
