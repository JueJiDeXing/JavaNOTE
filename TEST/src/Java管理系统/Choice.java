package Java管理系统;

enum Choice {
        SHOW_ALL(1),
        FIND_STUDENT(2),
        ADD_STUDENT(3),
        EXIT(4);
        final int choice;

        Choice(int choice) {
            this.choice = choice;
        }

    }
