from CsvWriter import CsvWriter
from DataSetCleaner import DataSetCleaner
from DataSetMiner import DataSetMiner
from WordClouder import WordClouder


class Demo:
    @staticmethod
    def main():
        data_set_cleaner = DataSetCleaner()
        cleaned_article_list = data_set_cleaner.get_term_list()

        data_set_miner = DataSetMiner(cleaned_article_list)
        occurrence_tf = data_set_miner.get_occurrence_tf()
        occurrence_tf_idf = data_set_miner.get_occurrence_tf_idf()
        tf = data_set_miner.get_tf()
        tf_idf = data_set_miner.get_tf_idf()

        CsvWriter.write_to_csv("occurence_tf.csv", occurrence_tf)
        CsvWriter.write_to_csv("occurence_tf_idf.csv", occurrence_tf_idf)
        CsvWriter.write_to_csv("tf_list.csv", tf)
        CsvWriter.write_to_csv("tf_idf_list.csv", tf_idf)

        WordClouder.make_word_cloud("occurence_tf.csv", "occurence_tf")
        WordClouder.make_word_cloud("occurence_tf_idf.csv", "occurence_tf_idf")
        WordClouder.make_word_cloud("tf_list.csv", "tf_list")
        WordClouder.make_word_cloud("tf_idf_list.csv", "tf_idf_list")


if __name__ == "__main__":
    Demo.main()