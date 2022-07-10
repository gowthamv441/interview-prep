# Given a rows x cols screen and a sentence represented as a list of strings, return the number of times the given sentence can be fitted on the screen.

# The order of words in the sentence must remain unchanged, and a word cannot be split into two lines. A single space must separate two consecutive words in a line.

 

# Example 1:

# Input: sentence = ["hello","world"], rows = 2, cols = 8
# Output: 1
# Explanation:
# hello---
# world---
# The character '-' signifies an empty space on the screen.

# Example 2:

# Input: sentence = ["a", "bcd", "e"], rows = 3, cols = 6
# Output: 2
# Explanation:
# a-bcd- 
# e-a---
# bcd-e-
# The character '-' signifies an empty space on the screen.

# Example 3:

# Input: sentence = ["i","had","apple","pie"], rows = 4, cols = 5
# Output: 1
# Explanation:
# i-had
# apple
# pie-i
# had--
# The character '-' signifies an empty space on the screen.


class Solution:
    def wordsTyping(self, sentence: List[str], rows: int, cols: int) -> int:
        sent = "_".join(sentence)+'_'

        sent_pointer = 0
        for i in range(rows):

            ###### Move sentence cols-1 steps forward 
            ###### -1 because we want it point to the last character that could be fitted screen

            sent_pointer += cols - 1

            ###### Column end points to underscore
            if sent[sent_pointer%len(sent)] == '_':
                sent_pointer += 1

            ###### Column end points to character before underscore
            elif sent[(sent_pointer+1)%len(sent)] == '_':
                sent_pointer += 2

            ###### Column end points to middle of the word
            else:
                while sent_pointer > 0 and sent[sent_pointer%len(sent)] != '_':
                    sent_pointer -= 1

                sent_pointer += 1

        total_count = sent_pointer//len(sent)       

        return total_count
