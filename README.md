# choir_director
CLI scripts to grind sbcs

[runweb.webm](https://github.com/user-attachments/assets/1c577962-e55c-4ebd-837f-895befdc0802)

# Sample commands 
Crafting
```./gradlew run --args="craft '82+ Combo Upgrade' --count=10 --quality=Gold --rarity=Common"```
```./gradlew run --args="craft 'Daily Gold Upgrade' --count=3 --quality=Bronze,Silver --rarity=Common,Common"```

Solving generic sbcs using players in squad <br/>
```./gradlew run --args="findPlayers"``` 

```./gradlew run --args="gen 'Grassroot Greats Challenge 4' --formation=4222 --restrictions='maxNation=4 minSameLeague=4 minSameclub=4 minRare=9 minPlayerRating=74 maxPlayerRating=82 minChem=28'"``` <br/>
```./gradlew run --args="gen 'Marquee Matchups' --formation=442 --sbc-index=0 --restrictions='minFromNation=Türkiye:1 maxSameLeague=3 minRare=1 minPlayerRating=70 minChem=14'"```<br/>
```./gradlew run --args="gen 'Marquee Matchups' --formation=4231 --sbc-index=1 --restrictions='minNation=2 maxLeague=4 minSameNation=3 minPlayerRating=70 minChem=18'"```<br/>
```./gradlew run --args="gen 'Marquee Matchups' --formation=4321 --sbc-index=2 --restrictions='minFromClub=OL:1 minFromNation=France:2 maxSameClub=3 minRare=2 minRating=76 minChem=22'"```<br/>
```./gradlew run --args="gen 'Marquee Matchups' --formation=41212 --sbc-index=3 --restrictions='minFromClub=Liverpool:1 minFromNation=England:3 maxClub=5 minRare=3 minRating=78 minChem=26'"```<br/>
```./gradlew run --args="gen 'Premium Mixed Leagues Upgrade' --formation=41212 --sbc-index=3 --restrictions='minFromLeague=Premier League:11 maxSameClub=1 minRare=4 minRating=79 minChem=10'"```<br/>
```./gradlew run --args="gen 'Mixed Leagues Upgrade' --formation=433 --sbc-index=0 --restrictions='minFromLeague=Libertadores:11 minSameClub=6 minRating=60 maxRating=74 minChem=10'"```<br/>
