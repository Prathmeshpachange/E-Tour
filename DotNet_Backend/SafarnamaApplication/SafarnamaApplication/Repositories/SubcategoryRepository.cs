using Microsoft.EntityFrameworkCore;
using SafarnamaApplication.Models;

namespace SafarnamaApplication.Repositories
{
    public class SubcategoryRepository : ISubcategoryRepository
    {
        private readonly SafarnamadbContext _context;

        public SubcategoryRepository(SafarnamadbContext context)
        {
            _context = context;
        }

        public async Task<IEnumerable<Subcategory>> GetAllAsync()
        {
            return await _context.Subcategories.ToListAsync();
        }

        public async Task<IEnumerable<Subcategory>> GetByCategoryIdAsync(int categoryId)
        {
            return await _context.Subcategories
                                 .Where(s => s.CategoryId == categoryId)
                                 .ToListAsync();
        }
    }
}
