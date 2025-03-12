import pandas as pd

def regrouper_csv(input_file, output_file):
    df = pd.read_csv(input_file, sep="\t")  
    
    
