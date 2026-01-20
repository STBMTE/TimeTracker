const { createApp, ref, onMounted } = Vue;

createApp({
  setup() {
    const intervals = ref([]);
    const form = ref({
      startSec: '',
      endSec: '',
      type: 'WORK',
    });
    const error = ref('');

    const timeToSeconds = (timeStr) => {
          if (!timeStr) return 0;
          const [hours, minutes] = timeStr.split(':').map(Number);
          return hours * 3600 + minutes * 60;
        };

    const formatTime = (seconds) => {
       const h = Math.floor(seconds / 3600);
       const m = Math.floor((seconds % 3600) / 60);
       return `${String(h).padStart(2, '0')}:${String(m).padStart(2, '0')}`;
    };

    const loadIntervals = async () => {
          try {
            const res = await fetch('/api/intervals');
            if (!res.ok) throw new Error(await res.text());
            intervals.value = await res.json();
          } catch (err) {
            console.error('Ошибка загрузки:', err);
            error.value = 'Не удалось загрузить интервалы.';
          }
        };

    const addInterval = async () => {
       error.value = '';
       try {
         const start = timeToSeconds(form.value.startTime);
         const end = timeToSeconds(form.value.endTime);
         const response = await fetch('/api/intervals', {
           method: 'POST',
           headers: { 'Content-Type': 'application/json' },
           body: JSON.stringify({
             start,
             end,
             type: form.value.type,
           }),
         });
         if (!response.ok) {
           const msg = await response.text();
           throw new Error(msg);
         }
         form.value.startTime = '';
         form.value.endTime = '';
         await loadIntervals();
       } catch (err) {
         console.error('Ошибка добавления:', err);
         error.value = err.message || 'Ошибка при добавлении интервала';
       }
    };

    onMounted(loadIntervals);

    return { intervals, form, error, addInterval, formatTime };
  },
}).mount('#app');