import sys
import pandas as pd

if __name__ == "__main__":
    frames = []
    for f in sys.argv[1:-1]:
        df = pd.read_csv(f, index_col=0)
        frames.append(df)
    frame = pd.concat(frames, axis=1)
    frame = frame.set_axis(['EvoLearner', 'CELOE', 'SPaCEL', 'ALC_SAT'], axis=1)
    titles = ["EvoLearner", "CELOE", "SPaCEL", 'ALC_SAT']
    frame = frame.reindex(columns=titles)

    with open(sys.argv[-1], 'w') as f:
        f.write(frame.to_markdown())
