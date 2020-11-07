# Mastermind game
Mastermind is a two player board game which has a goal of breaking a code. The first player, the code maker, thinks of a 4 letter word using only the letters A through F. The second player, the code breaker, tries to guess the code maker's code by offering a sequence of guesses. The code maker responds to each guess by offering specific clues. The clues are used by the code breaker to make further guesses until the code is broken. When the code is guessed, the number of tries is displayed to the user.

Example of a round:
* Code maker thinks of "DFCA"
* The code breaker guessed "ABCD"
* The code maker then responds with "–+"; the minus signs indicate two letters of the guess are part of the code, but are not in the correct position. The plus sign indicates one letter of the guess is part of the code and are in the correct position.

> This repo is a mirror of the Dropbox solution provided in Robert C. Martin's (Uncle Bob's) Clean Coders video series (during Episode 9: Single Responsibility Principle).
