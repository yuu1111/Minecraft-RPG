package yuu.rpg

object JobUtil {

    fun jobjptoen(job :String):String {

        var job2: String = ""
        when (job) {

            "戦士" ->
                job2 = "Warrior"

            "魔術師" ->
                job2 = "Mage"

            "狩人" ->
                job2 = "Hunter"

            "村人" ->
                job2 = "Villager"

            "放浪者" ->
                job2 = "Wanderer"

            "騎士" ->
                job2 = "Knight"

            "クラフター" ->
                job2 = "Crafter"
        }
        return job2;
    }

    fun jobentojp(job :String):String {

        var job2: String = ""
        when (job) {

            "Warrior" ->
                job2 = "戦士"

            "Mage" ->
                job2 = "魔術師"

            "Hunter" ->
                job2 = "狩人"

            "Villager" ->
                job2 = "村人"

            "Wanderer" ->
                job2 = "放浪者"

            "Knight" ->
                job2 = "騎士"

            "Crafter" ->
                job2 = "クラフター"
        }
        return job2;
    }
}