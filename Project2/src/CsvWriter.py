import csv



class CsvWriter:
    '''
        CsvWriter class sorts and prints the given dictionary's contents to output file specified by filename
    '''
    @staticmethod
    def write_to_csv(filename, dict_to_output):
        with open(filename, 'w', encoding="utf-8") as output_file:
            wrtr = csv.writer(output_file, delimiter=';')
            sorted_output_file = sorted(dict_to_output.items(), key=lambda kv: kv[1], reverse=True)
            for i in range(50):
                try:
                    wrtr.writerow([sorted_output_file[i][0], sorted_output_file[i][1]])
                except IndexError:
                    pass