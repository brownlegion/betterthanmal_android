package com.example.owner.betterthanmal;

/**
 * Created by Krishna N. Deoram on 2016-07-18.
 * Gumi is love. Gumi is life.
 */

public class WaifuDatabaseObject {

    private String id, name, title, seiyuu, archetype, canon, yearReleased, animator, age, employer, dirty, type;

    public WaifuDatabaseObject(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public WaifuDatabaseObject setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public WaifuDatabaseObject setName(String name) {
        this.name = name;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public WaifuDatabaseObject setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSeiyuu() {
        return seiyuu;
    }

    public WaifuDatabaseObject setSeiyuu(String seiyuu) {
        this.seiyuu = seiyuu;
        return this;
    }

    public String getArchetype() {
        return archetype;
    }

    public WaifuDatabaseObject setArchetype(String archetype) {
        this.archetype = archetype;
        return this;
    }

    public String getCanon() {
        return canon;
    }

    public WaifuDatabaseObject setCanon(String canon) {
        this.canon = canon;
        return this;
    }

    public String getYearReleased() {
        return yearReleased;
    }

    public WaifuDatabaseObject setYearReleased(String yearReleased) {
        this.yearReleased = yearReleased;
        return this;
    }

    public String getAnimator() {
        return animator;
    }

    public WaifuDatabaseObject setAnimator(String animator) {
        this.animator = animator;
        return this;
    }

    public String getAge() {
        return age;
    }

    public WaifuDatabaseObject setAge(String age) {
        this.age = age;
        return this;
    }

    public String getEmployer() {
        return employer;
    }

    public WaifuDatabaseObject setEmployer(String employer) {
        this.employer = employer;
        return this;
    }

    public String getDirty() {
        return dirty;
    }

    public WaifuDatabaseObject setDirty(String dirty) {
        if (dirty.equals("1"))
            this.dirty = "Yes";
        else if (dirty.equals("0"))
            this.dirty = "No";
        return this;
    }

    @Override
    public String toString() {
        return "WaifuDatabaseObject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", seiyuu='" + seiyuu + '\'' +
                ", archetype='" + archetype + '\'' +
                ", canon='" + canon + '\'' +
                ", yearReleased='" + yearReleased + '\'' +
                ", animator='" + animator + '\'' +
                ", age='" + age + '\'' +
                ", employer='" + employer + '\'' +
                ", dirty='" + dirty + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
