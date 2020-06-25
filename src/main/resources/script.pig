A = LOAD 'result.csv' USING PigStorage(',') AS (MovieId:int, MovieSumVotes:float, MovieSumRates:float);


B = LOAD 'movie_titles.csv' USING PigStorage(',') AS (MovieId:int, MovieYear:int, MovieTitle:chararray);


C = JOIN A BY MovieId, B BY MovieId;


D = FOREACH C GENERATE $0 as MovieId, $1 as MovieSumVotes, $2 as MovieSumRates, $4 as MovieTitle ,$5 as MovieYear;


E = FILTER D BY $1 > 1000;


F = FOREACH E GENERATE $0 as MovieId, $3,  $2 / $1 as MovieVoteAvg:float, $4;


G = GROUP F BY $1;


I = FOREACH G { 
	sorted = ORDER F BY $2 DESC;
	top = LIMIT sorted 3;
	GENERATE top; 
}

STORE I INTO 'results' USING PigStorage (',');