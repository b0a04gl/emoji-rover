from flask import Flask, render_template, jsonify
import redis

app = Flask(__name__,template_folder='/Users/b0a04gl/Documents/#BA6/emoji-rover/emoji-fanout')
redis_client = redis.StrictRedis(host='localhost', port=6379, db=0)

@app.route("/",methods=['GET', 'POST'])
def index():
    return render_template('emoji-fanout-index.html')

@app.route('/get_emojis_from_redis')
def getEmoji():
    emoji_counts = get_emoji_counts()
    print(emoji_counts)
    return render_template('emoji-fanout-index.html',emoji_map=emoji_counts)

def get_emoji_counts():
    emoji_counts = {}
    for key, value in redis_client.hgetall('emoji_counts').items():
        emoji = key.decode('utf-8')
        count = int(value)
        emoji_counts[emoji] = count
    return emoji_counts

if __name__ == '__main__':
    app.run(debug=True)
