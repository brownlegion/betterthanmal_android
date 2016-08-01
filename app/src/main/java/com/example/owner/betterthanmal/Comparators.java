package com.example.owner.betterthanmal;

import java.util.Comparator;

/**
 * Created by Krishna N. Deoram on 2016-07-18.
 * Gumi is love. Gumi is life.
 */

public abstract class Comparators {

    private static String mytype = "";

    public static Comparator<WaifuDatabaseObject> getComparator(String type) {
        mytype = type;
        if (type.equals("id"))
            return new ComparatorById();
        else if (type.equals("name"))
            return new ComparatorByName();
        else if (type.equals("title"))
            return new ComparatorByTitle();
        else if (type.equals("seiyuu"))
            return new ComparatorBySeiyuu();
        else if (type.equals("archetype"))
            return new ComparatorByArchetype();
        else if (type.equals("canon"))
            return new ComparatorByCanon();
        else if (type.equals("released"))
            return new ComparatorByRelease();
        else if (type.equals("animator"))
            return new ComparatorByAnimator();
        else if (type.equals("age"))
            return new ComparatorByAge();
        else if (type.equals("employer"))
            return new ComparatorByEmployer();
        else if (type.equals("dirty"))
            return new ComparatorByDirty();
        else
            return null;
    }

    public static String getType() {
        return mytype;
    }

   private static class ComparatorById implements Comparator<WaifuDatabaseObject> {

        @Override
        public int compare(WaifuDatabaseObject lhs, WaifuDatabaseObject rhs) {
            return Integer.valueOf(lhs.getId()) - Integer.valueOf(rhs.getId()); //Numerical order for IDs.
        }
   }

   private static class ComparatorByName implements Comparator<WaifuDatabaseObject> {

        @Override
        public int compare(WaifuDatabaseObject lhs, WaifuDatabaseObject rhs) {
            int flag = lhs.getName().trim().compareTo(rhs.getName().trim());
            if (flag == 0) {
                return Integer.valueOf(lhs.getId()) - Integer.valueOf(rhs.getId());
            } else
                return flag; //Alphabetical order.
        }
    }

   private static class ComparatorByTitle implements Comparator<WaifuDatabaseObject> {

        @Override
        public int compare(WaifuDatabaseObject lhs, WaifuDatabaseObject rhs) {
            int flag = lhs.getTitle().compareTo(rhs.getTitle());
            if (flag == 0) {
                return Integer.valueOf(lhs.getId()) - Integer.valueOf(rhs.getId());
            } else
                return flag; //Alphabetical order.
        }
   }

   private static class ComparatorBySeiyuu implements Comparator<WaifuDatabaseObject> {

        @Override
        public int compare(WaifuDatabaseObject lhs, WaifuDatabaseObject rhs) {
            int flag = lhs.getSeiyuu().compareTo(rhs.getSeiyuu());
            if (flag == 0) {
                return Integer.valueOf(lhs.getId()) - Integer.valueOf(rhs.getId());
            } else
                return flag; //Alphabetical order.;
        }
   }

   private static class ComparatorByArchetype implements Comparator<WaifuDatabaseObject> {

        @Override
        public int compare(WaifuDatabaseObject lhs, WaifuDatabaseObject rhs) {
            int flag = lhs.getArchetype().compareTo(rhs.getArchetype());
            if (flag == 0) {
                return Integer.valueOf(lhs.getId()) - Integer.valueOf(rhs.getId());
            } else
                return flag; //Alphabetical order.
        }
   }

   private static class ComparatorByCanon implements Comparator<WaifuDatabaseObject> {

        @Override
        public int compare(WaifuDatabaseObject lhs, WaifuDatabaseObject rhs) {
            int flag = lhs.getCanon().compareTo(rhs.getCanon());
            if (flag == 0) {
                return Integer.valueOf(lhs.getId()) - Integer.valueOf(rhs.getId());
            } else
                return flag; //Alphabetical order.
        }
   }

    private static class ComparatorByRelease implements Comparator<WaifuDatabaseObject> {

        @Override
        public int compare(WaifuDatabaseObject lhs, WaifuDatabaseObject rhs) {
            int flag = lhs.getYearReleased().compareTo(rhs.getYearReleased());
            if (flag == 0) {
                return Integer.valueOf(lhs.getId()) - Integer.valueOf(rhs.getId());
            } else
                return flag; //Release order.
        }
    }

    private static class ComparatorByAnimator implements Comparator<WaifuDatabaseObject> {

        @Override
        public int compare(WaifuDatabaseObject lhs, WaifuDatabaseObject rhs) {
            int flag = lhs.getAnimator().compareTo(rhs.getAnimator());
            if (flag == 0) {
                return Integer.valueOf(lhs.getId()) - Integer.valueOf(rhs.getId());
            } else
                return flag; //Animator order.
        }
    }

    private static class ComparatorByAge implements Comparator<WaifuDatabaseObject> {

        @Override
        public int compare(WaifuDatabaseObject lhs, WaifuDatabaseObject rhs) {
            int flag = lhs.getAge().compareTo(rhs.getAge());
            if (flag == 0) {
                return Integer.valueOf(lhs.getId()) - Integer.valueOf(rhs.getId());
            } else
                return flag; //Age.
        }
    }

    private static class ComparatorByEmployer implements Comparator<WaifuDatabaseObject> {

        @Override
        public int compare(WaifuDatabaseObject lhs, WaifuDatabaseObject rhs) {
            int flag = lhs.getEmployer().compareTo(rhs.getEmployer());
            if (flag == 0) {
                return Integer.valueOf(lhs.getId()) - Integer.valueOf(rhs.getId());
            } else
                return flag; //Employer order.
        }
    }

    private static class ComparatorByDirty implements Comparator<WaifuDatabaseObject> {

        @Override
        public int compare(WaifuDatabaseObject lhs, WaifuDatabaseObject rhs) {
            int flag = lhs.getDirty().compareTo(rhs.getDirty());
            if (flag == 0) {
                return Integer.valueOf(lhs.getId()) - Integer.valueOf(rhs.getId());
            } else
                return flag; //Dirtyorder.
        }
    }
}
