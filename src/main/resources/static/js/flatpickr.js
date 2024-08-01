let maxDate = new Date();
maxDate.setMonth(maxDate.getMonth() + 3);

const regularHoliday = document.getElementById('fromReservationDate').dataset.regularHoliday;

// 曜日を変換するためのマッピング
const dayMap = {
    "日曜日": 0,
    "月曜日": 1,
    "火曜日": 2,
    "水曜日": 3,
    "木曜日": 4,
    "金曜日": 5,
    "土曜日": 6
};

flatpickr("#fromReservationDate", {
    locale: "ja",
    minDate: "today",
    maxDate: maxDate,
    dateFormat: "Y-m-d",
    disable: [
        function(date) {
            // regularHolidayを曜日のインデックスに変換
            return date.getDay() === dayMap[regularHoliday];
        }
    ]
});
