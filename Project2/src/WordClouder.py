import csv
import pandas as pd

from wordcloud import WordCloud, STOPWORDS
import matplotlib.pyplot as plt

class WordClouder:
    '''
    This method creates word cloud of .csv file.
    '''
    @staticmethod
    def make_word_cloud(csv_input_filename, csv_output_filename):

        df = open(csv_input_filename, "r", encoding='utf-8')
        csv_reader = csv.reader(df)
        word_list = list(csv_reader)
        to_world_cloud = ' '
        for row in word_list:
            if len(row) != 0:
                words = row[0].split(";")
            else: continue
            to_world_cloud = to_world_cloud + ' '+ words[0]

        wordcloud = WordCloud(width=800, height=800,
                              background_color='white',

                              min_font_size=10).generate(to_world_cloud)


        plt.figure(figsize=(8, 8))
        plt.imshow(wordcloud)
        plt.axis("off")
        plt.tight_layout(pad=0)
        plt.savefig('wordcloud_' + str(csv_output_filename) + '.pdf')
        plt.show()

