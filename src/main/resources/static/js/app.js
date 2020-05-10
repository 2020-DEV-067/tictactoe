var app = new Vue({
  el: '#game-view',
  data: {
    squares: [null, null, null, null, null, null, null, null, null],
    message: ""
  },
  mounted: function() {
    this.refreshUI();
  },
  methods: {
    makeMove: function(index) {
        var i = Math.floor(index / 3);
        var j = index % 3;
        axios.post('board/playTurn', {
            x: i,
            y: j
        }).then(function() {
            this.refreshUI();
        }.bind(this));
    },
    refreshUI: function() {
        axios.get('board/state').then(function(response) {
            var board = response.data.gameField;
            this.squares = board.flat();
            this.message = response.data.message;
        }.bind(this));
    },
    restart: function() {
        axios.post('board/restart').then(function() {
            this.refreshUI();
        }.bind(this));
    }
  }
});