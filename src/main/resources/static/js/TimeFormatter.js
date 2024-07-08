document.addEventListener("DOMContentLoaded", function() {
    const openingTimeSelect = document.getElementById('openingTimeSelect');
    const closingTimeSelect = document.getElementById('closingTimeSelect');
    const times = [];

    // 時間の配列を生成
    for (let h = 0; h < 24; h++) {
        times.push(`${h}:00`);
        times.push(`${h}:30`);
    }


    // 各セレクトボックスにオプションを追加
    [openingTimeSelect, closingTimeSelect].forEach(selectBox => {
        times.forEach(time => {
            const option = document.createElement('option');
            option.value = time;
            option.text = time;
            selectBox.appendChild(option);
        });
    });
});
