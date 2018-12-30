package yuu.rpg.Job

object JobUtil {

    fun jobjptoen(str :String):String {

        var job = ""
        when (str) {

            "戦士" ->
                job = "Warrior"

            "魔術師" ->
                job = "Mage"

            "狩人" ->
                job = "Hunter"

            "村人" ->
                job = "Villager"

            "放浪者" ->
                job = "Wanderer"

            "騎士" ->
                job = "Knight"

            "クラフター" ->
                job = "Crafter"
        }
        return job;
    }

    fun jobentojp(str :String):String {

        var job = ""
        when (str) {

            "Warrior" ->
                job = "戦士"

            "Mage" ->
                job = "魔術師"

            "Hunter" ->
                job = "狩人"

            "Villager" ->
                job = "村人"

            "Wanderer" ->
                job = "放浪者"

            "Knight" ->
                job = "騎士"

            "Crafter" ->
                job = "クラフター"
        }
        return job;
    }
}